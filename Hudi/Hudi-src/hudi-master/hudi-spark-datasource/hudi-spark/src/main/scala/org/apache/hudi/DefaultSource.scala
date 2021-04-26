/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hudi

import org.apache.hadoop.fs.Path
import org.apache.hudi.DataSourceReadOptions._
import org.apache.hudi.common.model.{HoodieRecord, HoodieTableType}
import org.apache.hudi.DataSourceWriteOptions.{BOOTSTRAP_OPERATION_OPT_VAL, OPERATION_OPT_KEY}
import org.apache.hudi.common.fs.FSUtils
import org.apache.hudi.common.table.{HoodieTableMetaClient, TableSchemaResolver}
import org.apache.hudi.exception.HoodieException
import org.apache.hudi.hadoop.HoodieROTablePathFilter
import org.apache.log4j.LogManager
import org.apache.spark.sql.execution.datasources.DataSource
import org.apache.spark.sql.execution.streaming.{Sink, Source}
import org.apache.spark.sql.hudi.streaming.HoodieStreamSource
import org.apache.spark.sql.sources._
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SQLContext, SaveMode, SparkSession}

import scala.collection.JavaConverters._

/**
  * Hoodie Spark Datasource, for reading and writing hoodie tables
  *
  */
/**
 * Spark支持用户自定义的format来读取或写入文件，只需要实现对应的（RelationProvider、SchemaRelationProvider）等接口即可。
 * 而Hudi也自定义实现了 org.apache.hudi/ hudi来实现Spark对Hudi数据集的读写，Hudi中最重要的一个相关类为 DefaultSource，
 * 其实现了 CreatableRelationProvider#createRelation接口，并实现了读写逻辑。
 */
class DefaultSource extends RelationProvider
  with SchemaRelationProvider
  with CreatableRelationProvider
  with DataSourceRegister
  with StreamSinkProvider
  with StreamSourceProvider
  with Serializable {

  SparkSession.getActiveSession.foreach { spark =>
    val sparkVersion = spark.version
    if (sparkVersion.startsWith("0.") || sparkVersion.startsWith("1.") || sparkVersion.startsWith("2.")) {
      // Enable "passPartitionByAsOptions" to support "write.partitionBy(...)"
      // "Whether to pass the partitionBy columns as options in DataFrameWriter. Data source V1 now silently drops partitionBy columns for non-file-format sources; turning the flag on provides a way for these sources to see these partitionBy columns."

      // 设置所有spark session为重新分区写可以减少shuffle次数，提高效率
      spark.conf.set("spark.sql.legacy.sources.write.passPartitionByAsOptions", "true")
    }
  }

  private val log = LogManager.getLogger(classOf[DefaultSource])

  override def createRelation(sqlContext: SQLContext,
                              parameters: Map[String, String]): BaseRelation = {
    createRelation(sqlContext, parameters, null)
  }

  // spark读hudi文件逻辑
  // 当使用Spark查询Hudi数据集时，当数据的schema新增时，会获取单个分区的parquet文件来推导出schema，若变更schema后未更新该分区数据，那么新增的列是不会显示，否则会显示该新增的列；
  // 若更新该分区的记录时，那么新增的列也不会显示，可通过 mergeSchema来控制合并不同分区下parquet文件的schema，从而可达到显示新增列的目的。
  // .option("mergeSchema","true")
  override def createRelation(sqlContext: SQLContext,
                              optParams: Map[String, String],
                              schema: StructType): BaseRelation = {
    // Add default options for unspecified read options keys.
    // 合并参数
    val parameters = translateViewTypesToQueryTypes(optParams)

    val path = parameters.get("path")
    val readPathsStr = parameters.get(DataSourceReadOptions.READ_PATHS_OPT_KEY)
    // read source path不能为空
    if (path.isEmpty && readPathsStr.isEmpty) {
      throw new HoodieException(s"'path' or '$READ_PATHS_OPT_KEY' or both must be specified.")
    }

    val readPaths = readPathsStr.map(p => p.split(",").toSeq).getOrElse(Seq())
    // 合并所有path
    val allPaths = path.map(p => Seq(p)).getOrElse(Seq()) ++ readPaths
    // 获取对应allpath的文件
    val fs = FSUtils.getFs(allPaths.head, sqlContext.sparkContext.hadoopConfiguration)
    // 检查输入路径是否包含全局模式，如果是，则将其映射到与全局模式匹配的绝对路径列表。否则，返回原始路径 全路径-->绝对路径
    val globPaths = HoodieSparkUtils.checkAndGlobPathIfNecessary(allPaths, fs)
    // 获取对应allpath的表路径
    val tablePath = DataSourceUtils.getTablePath(fs, globPaths.toArray)
    log.info("Obtained hudi table path: " + tablePath)
    // 创建元数据client
    val metaClient = HoodieTableMetaClient.builder().setConf(fs.getConf).setBasePath(tablePath).build()
    val isBootstrappedTable = metaClient.getTableConfig.getBootstrapBasePath.isPresent
    log.info("Is bootstrapped table => " + isBootstrappedTable)

    if (parameters(QUERY_TYPE_OPT_KEY).equals(QUERY_TYPE_SNAPSHOT_OPT_VAL)) {
      if (metaClient.getTableType.equals(HoodieTableType.MERGE_ON_READ)) {
        if (isBootstrappedTable) {
          // Snapshot query is not supported for Bootstrapped MOR tables
          log.warn("Snapshot query is not supported for Bootstrapped Merge-on-Read tables." +
            " Falling back to Read Optimized query.")
          // MOR
          new HoodieBootstrapRelation(sqlContext, schema, globPaths, metaClient, optParams)
        } else {
          new MergeOnReadSnapshotRelation(sqlContext, optParams, schema, globPaths, metaClient)
        }
      } else {
        // COW
        getBaseFileOnlyView(sqlContext, parameters, schema, readPaths, isBootstrappedTable, globPaths, metaClient)
      }
    } else if(parameters(QUERY_TYPE_OPT_KEY).equals(QUERY_TYPE_READ_OPTIMIZED_OPT_VAL)) {
      getBaseFileOnlyView(sqlContext, parameters, schema, readPaths, isBootstrappedTable, globPaths, metaClient)
    } else if (parameters(QUERY_TYPE_OPT_KEY).equals(QUERY_TYPE_INCREMENTAL_OPT_VAL)) {
      val metaClient = HoodieTableMetaClient.builder().setConf(fs.getConf).setBasePath(tablePath).build()
      if (metaClient.getTableType.equals(HoodieTableType.MERGE_ON_READ)) {
        new MergeOnReadIncrementalRelation(sqlContext, optParams, schema, metaClient)
      } else {
        // 增量Relation
        new IncrementalRelation(sqlContext, optParams, schema, metaClient)
      }
    } else {
      throw new HoodieException("Invalid query type :" + parameters(QUERY_TYPE_OPT_KEY))
    }
  }

  /**
    * This DataSource API is used for writing the DataFrame at the destination. For now, we are returning a dummy
    * relation here because Spark does not really make use of the relation returned, and just returns an empty
    * dataset at [[org.apache.spark.sql.execution.datasources.SaveIntoDataSourceCommand.run()]]. This saves us the cost
    * of creating and returning a parquet relation here.
    *
    * TODO: Revisit to return a concrete relation here when we support CREATE TABLE AS for Hudi with DataSource API.
    *       That is the only case where Spark seems to actually need a relation to be returned here
    *       [[DataSource.writeAndRead()]]
    *
    * @param sqlContext Spark SQL Context
    * @param mode Mode for saving the DataFrame at the destination
    * @param optParams Parameters passed as part of the DataFrame write operation
    * @param df Spark DataFrame to be written
    * @return Spark Relation
    */
  override def createRelation(sqlContext: SQLContext,
                              mode: SaveMode,
                              optParams: Map[String, String],
                              df: DataFrame): BaseRelation = {
    val parameters = HoodieWriterUtils.parametersWithWriteDefaults(optParams)
    val translatedOptions = DataSourceWriteOptions.translateSqlOptions(parameters)
    val dfWithoutMetaCols = df.drop(HoodieRecord.HOODIE_META_COLUMNS.asScala:_*)

    if (translatedOptions(OPERATION_OPT_KEY).equals(BOOTSTRAP_OPERATION_OPT_VAL)) {
      HoodieSparkSqlWriter.bootstrap(sqlContext, mode, translatedOptions, dfWithoutMetaCols)
    } else {
      HoodieSparkSqlWriter.write(sqlContext, mode, translatedOptions, dfWithoutMetaCols)
    }
    new HoodieEmptyRelation(sqlContext, dfWithoutMetaCols.schema)
  }

  override def createSink(sqlContext: SQLContext,
                          optParams: Map[String, String],
                          partitionColumns: Seq[String],
                          outputMode: OutputMode): Sink = {
    val parameters = HoodieWriterUtils.parametersWithWriteDefaults(optParams)
    val translatedOptions = DataSourceWriteOptions.translateSqlOptions(parameters)
    new HoodieStreamingSink(
      sqlContext,
      translatedOptions,
      partitionColumns,
      outputMode)
  }

  override def shortName(): String = "hudi"

  private def getBaseFileOnlyView(sqlContext: SQLContext,
                                  optParams: Map[String, String],
                                  schema: StructType,
                                  extraReadPaths: Seq[String],
                                  isBootstrappedTable: Boolean,
                                  globPaths: Seq[Path],
                                  metaClient: HoodieTableMetaClient): BaseRelation = {
    log.warn("Loading Base File Only View.")

    if (isBootstrappedTable) {
      // For bootstrapped tables, use our custom Spark relation for querying
      new HoodieBootstrapRelation(sqlContext, schema, globPaths, metaClient, optParams)
    } else {
      // this is just effectively RO view only, where `path` can contain a mix of
      // non-hoodie/hoodie path files. set the path filter up
      sqlContext.sparkContext.hadoopConfiguration.setClass(
        "mapreduce.input.pathFilter.class",
        classOf[HoodieROTablePathFilter],
        classOf[org.apache.hadoop.fs.PathFilter])

      log.info("Constructing hoodie (as parquet) data source with options :" + optParams)
      // simply return as a regular parquet relation
      // 解析Relation
      DataSource.apply(
        sparkSession = sqlContext.sparkSession,
        paths = extraReadPaths,
        userSpecifiedSchema = Option(schema),
        className = "parquet",
        options = optParams)
        .resolveRelation() // 解析parquet文件
    }
  }

  override def sourceSchema(sqlContext: SQLContext,
                            schema: Option[StructType],
                            providerName: String,
                            parameters: Map[String, String]): (String, StructType) = {
    val path = parameters.get("path")
    if (path.isEmpty || path.get == null) {
      throw new HoodieException(s"'path'  must be specified.")
    }
    val metaClient = HoodieTableMetaClient.builder().setConf(
      sqlContext.sparkSession.sessionState.newHadoopConf()).setBasePath(path.get).build()
    val schemaResolver = new TableSchemaResolver(metaClient)
    val sqlSchema =
      try {
        val avroSchema = schemaResolver.getTableAvroSchema
        AvroConversionUtils.convertAvroSchemaToStructType(avroSchema)
      } catch {
        case _: Exception =>
          require(schema.isDefined, "Fail to resolve source schema")
          schema.get
      }
    (shortName(), sqlSchema)
  }

  override def createSource(sqlContext: SQLContext,
                            metadataPath: String,
                            schema: Option[StructType],
                            providerName: String,
                            parameters: Map[String, String]): Source = {
    new HoodieStreamSource(sqlContext, metadataPath, schema, parameters)
  }
}
