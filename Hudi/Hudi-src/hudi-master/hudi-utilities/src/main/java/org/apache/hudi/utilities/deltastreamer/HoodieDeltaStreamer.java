/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hudi.utilities.deltastreamer;

import org.apache.hudi.async.HoodieAsyncService;
import org.apache.hudi.async.AsyncCompactService;
import org.apache.hudi.async.SparkAsyncCompactService;
import org.apache.hudi.client.SparkRDDWriteClient;
import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.client.common.HoodieSparkEngineContext;
import org.apache.hudi.common.bootstrap.index.HFileBootstrapIndex;
import org.apache.hudi.common.config.TypedProperties;
import org.apache.hudi.common.fs.FSUtils;
import org.apache.hudi.common.model.HoodieTableType;
import org.apache.hudi.common.model.OverwriteWithLatestAvroPayload;
import org.apache.hudi.common.model.WriteOperationType;
import org.apache.hudi.common.table.HoodieTableMetaClient;
import org.apache.hudi.common.table.timeline.HoodieInstant;
import org.apache.hudi.common.table.timeline.HoodieInstant.State;
import org.apache.hudi.common.table.timeline.HoodieTimeline;
import org.apache.hudi.common.util.CompactionUtils;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.common.util.ValidationUtils;
import org.apache.hudi.utilities.IdentitySplitter;
import org.apache.hudi.common.util.collection.Pair;
import org.apache.hudi.exception.HoodieException;
import org.apache.hudi.exception.HoodieIOException;
import org.apache.hudi.hive.HiveSyncTool;
import org.apache.hudi.utilities.HiveIncrementalPuller;
import org.apache.hudi.utilities.UtilHelpers;
import org.apache.hudi.utilities.checkpointing.InitialCheckPointProvider;
import org.apache.hudi.utilities.schema.SchemaProvider;
import org.apache.hudi.utilities.sources.JsonDFSSource;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An Utility which can incrementally take the output from {@link HiveIncrementalPuller} and apply it to the target
 * table. Does not maintain any state, queries at runtime to see how far behind the target table is from the source
 * table. This can be overriden to force sync from a timestamp.
 * <p>
 * In continuous mode, DeltaStreamer runs in loop-mode going through the below operations (a) pull-from-source (b)
 * write-to-sink (c) Schedule Compactions if needed (d) Conditionally Sync to Hive each cycle. For MOR table with
 * continuous mode enabled, a separate compactor thread is allocated to execute compactions
 */

/**
 * Hudi提供的从kafka摄取topic至Hudi
 * 源码研读：
 *  1.解析用户自定义配置项
 *  2.创建spark执行上下文以及HoodieDeltaStreamer
 *  3.根据不同的数据摄取方式进行入湖
 */
public class HoodieDeltaStreamer implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LogManager.getLogger(HoodieDeltaStreamer.class);

  public static final String CHECKPOINT_KEY = "deltastreamer.checkpoint.key";
  public static final String CHECKPOINT_RESET_KEY = "deltastreamer.checkpoint.reset_key";

  // cfg这个字段的生命周期仅存于调用者的内存中而不会写到磁盘里持久化
  protected final transient Config cfg;

  private final TypedProperties properties;

  // 同步服务
  protected transient Option<DeltaSyncService> deltaSyncService;

  // 执行程序
  private final Option<BootstrapExecutor> bootstrapExecutor;

  public static final String DELTASYNC_POOL_NAME = "hoodiedeltasync";

  public HoodieDeltaStreamer(Config cfg, JavaSparkContext jssc) throws IOException {
    this(cfg, jssc, FSUtils.getFs(cfg.targetBasePath, jssc.hadoopConfiguration()),
        jssc.hadoopConfiguration(), Option.empty());
  }

  public HoodieDeltaStreamer(Config cfg, JavaSparkContext jssc, Option<TypedProperties> props) throws IOException {
    this(cfg, jssc, FSUtils.getFs(cfg.targetBasePath, jssc.hadoopConfiguration()),
        jssc.hadoopConfiguration(), props);
  }

  public HoodieDeltaStreamer(Config cfg, JavaSparkContext jssc, FileSystem fs, Configuration conf) throws IOException {
    this(cfg, jssc, fs, conf, Option.empty());
  }

  public HoodieDeltaStreamer(Config cfg, JavaSparkContext jssc, FileSystem fs, Configuration conf,
                             Option<TypedProperties> props) throws IOException {
    // Resolving the properties first in a consistent way
    if (props.isPresent()) {
      this.properties = props.get();
    } else if (cfg.propsFilePath.equals(Config.DEFAULT_DFS_SOURCE_PROPERTIES)) {
      this.properties = UtilHelpers.getConfig(cfg.configs).getConfig();
    } else {
      this.properties = UtilHelpers.readConfig(
          FSUtils.getFs(cfg.propsFilePath, jssc.hadoopConfiguration()),
          new Path(cfg.propsFilePath), cfg.configs).getConfig();
    }

    if (cfg.initialCheckpointProvider != null && cfg.checkpoint == null) {
      InitialCheckPointProvider checkPointProvider =
          UtilHelpers.createInitialCheckpointProvider(cfg.initialCheckpointProvider, this.properties);
      checkPointProvider.init(conf);
      cfg.checkpoint = checkPointProvider.getCheckpoint();
    }
    this.cfg = cfg;
    this.bootstrapExecutor = Option.ofNullable(
        cfg.runBootstrap ? new BootstrapExecutor(cfg, jssc, fs, conf, this.properties) : null);
    this.deltaSyncService = Option.ofNullable(
        cfg.runBootstrap ? null : new DeltaSyncService(cfg, jssc, fs, conf, Option.ofNullable(this.properties)));
  }

  public void shutdownGracefully() {
    deltaSyncService.ifPresent(ds -> ds.shutdown(false));
  }

  /**
   * Main method to start syncing.
   * 开始摄取数据
   * @throws Exception
   */
  public void sync() throws Exception {
    // 1，首先判断bootstrapExecutor是否已在缓存中
    // 第一次缓存中是没有的
    if (bootstrapExecutor.isPresent()) {
      LOG.info("Performing bootstrap. Source=" + bootstrapExecutor.get().getBootstrapConfig().getBootstrapSourceBasePath());
      bootstrapExecutor.get().execute();
    } else {
      if (cfg.continuousMode) { // 2.如果程序是still running
        deltaSyncService.ifPresent(ds -> {
          ds.start(this::onDeltaSyncShutdown);
          try {
            ds.waitForShutdown();
          } catch (Exception e) {
            throw new HoodieException(e.getMessage(), e);
          }
        });
        LOG.info("Delta Sync shutting down");
      } else {
        // 3.程序只跑一次
        LOG.info("Delta Streamer running only single round");
        try {
          deltaSyncService.ifPresent(ds -> {
            try {
              // 通过deltaSyncService获取DeltaSync并开启sync
              ds.getDeltaSync().syncOnce();
            } catch (IOException e) {
              throw new HoodieIOException(e.getMessage(), e);
            }
          });
        } catch (Exception ex) {
          LOG.error("Got error running delta sync once. Shutting down", ex);
          throw ex;
        } finally {
          deltaSyncService.ifPresent(DeltaSyncService::close);
          LOG.info("Shut down delta streamer");
        }
      }
    }
  }

  public Config getConfig() {
    return cfg;
  }

  private boolean onDeltaSyncShutdown(boolean error) {
    LOG.info("DeltaSync shutdown. Closing write client. Error?" + error);
    deltaSyncService.ifPresent(DeltaSyncService::close);
    return true;
  }

  protected static class OperationConverter implements IStringConverter<WriteOperationType> {

    @Override
    public WriteOperationType convert(String value) throws ParameterException {
      return WriteOperationType.valueOf(value);
    }
  }

  // 以下为同步的相关配置项
  public static class Config implements Serializable {
    public static final String DEFAULT_DFS_SOURCE_PROPERTIES = "file://" + System.getProperty("user.dir")
        + "/src/test/resources/delta-streamer-config/dfs-source.properties";

    // 数据存放路径
    @Parameter(names = {"--target-base-path"},
        description = "base path for the target hoodie table. "
            + "(Will be created if did not exist first time around. If exists, expected to be a hoodie table)",
        required = true)
    public String targetBasePath;

    // TODO: How to obtain hive configs to register?
    @Parameter(names = {"--target-table"}, description = "name of the target table", required = true)
    public String targetTableName; // 目标表名

    @Parameter(names = {"--table-type"}, description = "Type of table. COPY_ON_WRITE (or) MERGE_ON_READ", required = true)
    public String tableType; // 表类型 COW/MOR

    @Parameter(names = {"--base-file-format"}, description = "File format for the base files. PARQUET (or) HFILE", required = false)
    public String baseFileFormat = "PARQUET"; // 数据存储格式PARQUET/HFILE

    @Parameter(names = {"--props"}, description = "path to properties file on localfs or dfs, with configurations for "
        + "hoodie client, schema provider, key generator and data source. For hoodie client props, sane defaults are "
        + "used, but recommend use to provide basic things like metrics endpoints, hive configs etc. For sources, refer"
        + "to individual classes, for supported properties."
        + " Properties in this file can be overridden by \"--hoodie-conf\"")
    public String propsFilePath = DEFAULT_DFS_SOURCE_PROPERTIES; // kafka以及同步至Hudi相关分区、主键、offset等相关配置文件地址

    @Parameter(names = {"--hoodie-conf"}, description = "Any configuration that can be set in the properties file "
        + "(using the CLI parameter \"--props\") can also be passed command line using this parameter. This can be repeated",
            splitter = IdentitySplitter.class)
    public List<String> configs = new ArrayList<>(); // 此处配置也可直接上一个配置文件中

    @Parameter(names = {"--source-class"},
        description = "Subclass of org.apache.hudi.utilities.sources to read data. "
            + "Built-in options: org.apache.hudi.utilities.sources.{JsonDFSSource (default), AvroDFSSource, "
            + "JsonKafkaSource, AvroKafkaSource, HiveIncrPullSource}")
    public String sourceClassName = JsonDFSSource.class.getName(); // 源数据读取格式 json/avro...

    @Parameter(names = {"--source-ordering-field"}, description = "Field within source record to decide how"
        + " to break ties between records with same key in input data. Default: 'ts' holding unix timestamp of record")
    public String sourceOrderingField = "ts"; // 若存在两条主键相同的数据，默认按照ts字段来排序摄取最新一条入湖

    @Parameter(names = {"--payload-class"}, description = "subclass of HoodieRecordPayload, that works off "
        + "a GenericRecord. Implement your own, if you want to do something other than overwriting existing value")
    public String payloadClassName = OverwriteWithLatestAvroPayload.class.getName();

    @Parameter(names = {"--schemaprovider-class"}, description = "subclass of org.apache.hudi.utilities.schema"
        + ".SchemaProvider to attach schemas to input & target table data, built in options: "
        + "org.apache.hudi.utilities.schema.FilebasedSchemaProvider."
        + "Source (See org.apache.hudi.utilities.sources.Source) implementation can implement their own SchemaProvider."
        + " For Sources that return Dataset<Row>, the schema is obtained implicitly. "
        + "However, this CLI option allows overriding the schemaprovider returned by Source.")
    public String schemaProviderClassName = null; // hudi schema读取校验类

    @Parameter(names = {"--transformer-class"},
        description = "A subclass or a list of subclasses of org.apache.hudi.utilities.transform.Transformer"
            + ". Allows transforming raw source Dataset to a target Dataset (conforming to target schema) before "
            + "writing. Default : Not set. E:g - org.apache.hudi.utilities.transform.SqlQueryBasedTransformer (which "
            + "allows a SQL query templated to be passed as a transformation function). "
            + "Pass a comma-separated list of subclass names to chain the transformations.")
    public List<String> transformerClassNames = null; // 可对源数据使用sql进行transformer，然后再入湖

    @Parameter(names = {"--source-limit"}, description = "Maximum amount of data to read from source. "
        + "Default: No limit, e.g: DFS-Source => max bytes to read, Kafka-Source => max events to read")
    public long sourceLimit = Long.MAX_VALUE; // 源数据读取数据量限制

    @Parameter(names = {"--op"}, description = "Takes one of these values : UPSERT (default), INSERT (use when input "
        + "is purely new data/inserts to gain speed)", converter = OperationConverter.class)
    public WriteOperationType operation = WriteOperationType.UPSERT; // 写入模式 UPSERT/INSERT/bulk_insert....

    @Parameter(names = {"--filter-dupes"},
        description = "Should duplicate records from source be dropped/filtered out before insert/bulk-insert")
    public Boolean filterDupes = false; // 是否在插入/批量插入之前删除/过滤掉源中的重复记录,默认false

    //will abandon in the future version, recommended use --enable-sync
    @Parameter(names = {"--enable-hive-sync"}, description = "Enable syncing to hive")
    public Boolean enableHiveSync = false; // hive同步

    @Parameter(names = {"--enable-sync"}, description = "Enable syncing meta")
    public Boolean enableMetaSync = false; // hive同步

    @Parameter(names = {"--sync-tool-classes"}, description = "Meta sync client tool, using comma to separate multi tools")
    public String syncClientToolClass = HiveSyncTool.class.getName(); // 若开启hive同步，则需要指定HiveSyncTool同步工具类

    @Parameter(names = {"--max-pending-compactions"},
        description = "Maximum number of outstanding inflight/requested compactions. Delta Sync will not happen unless"
            + "outstanding compactions is less than this number") // 未完成的进行中/请求的压缩的最大数量。除非未完成的压缩小于此数字，否则不会发生增量同步；其实就是限制未完成的compaction数量
    public Integer maxPendingCompactions = 5;

    @Parameter(names = {"--continuous"}, description = "Delta Streamer runs in continuous mode running"
        + " source-fetch -> Transform -> Hudi Write in loop")
    public Boolean continuousMode = false; // 是否spark程序一直摄取数据运行，默认false

    @Parameter(names = {"--min-sync-interval-seconds"},
        description = "the min sync interval of each sync in continuous mode")
    public Integer minSyncIntervalSeconds = 0; // 连续模式下每次同步的最小同步间隔，默认单位0s

    @Parameter(names = {"--spark-master"}, description = "spark master to use.")
    public String sparkMaster = "local[2]"; // spark执行模式

    @Parameter(names = {"--commit-on-errors"}, description = "Commit even when some records failed to be written")
    public Boolean commitOnErrors = false; // 某些记录写入失败也提交，默认false

    @Parameter(names = {"--delta-sync-scheduling-weight"},
        description = "Scheduling weight for delta sync as defined in "
            + "https://spark.apache.org/docs/latest/job-scheduling.html")
    public Integer deltaSyncSchedulingWeight = 1; // 控制资源池相对其他资源池，可以分配到资源的比例。默认所有资源池的weight都是1。

    @Parameter(names = {"--compact-scheduling-weight"}, description = "Scheduling weight for compaction as defined in "
        + "https://spark.apache.org/docs/latest/job-scheduling.html")
    public Integer compactSchedulingWeight = 1; // 控制资源池相对其他资源池，可以分配到资源的比例。默认所有资源池的weight都是1。

    @Parameter(names = {"--delta-sync-scheduling-minshare"}, description = "Minshare for delta sync as defined in "
        + "https://spark.apache.org/docs/latest/job-scheduling.html")
    public Integer deltaSyncSchedulingMinShare = 0; // 数据同步 最小资源分配值（CPU个数）

    @Parameter(names = {"--compact-scheduling-minshare"}, description = "Minshare for compaction as defined in "
        + "https://spark.apache.org/docs/latest/job-scheduling.html")
    public Integer compactSchedulingMinShare = 0; // 压缩数据时 最小资源分配值（CPU个数）

    /**
     * Compaction is enabled for MoR table by default. This flag disables it
     */
    @Parameter(names = {"--disable-compaction"},
        description = "Compaction is enabled for MoR table by default. This flag disables it ")
    public Boolean forceDisableCompaction = false;

    /**
     * Resume Delta Streamer from this checkpoint.
     */
    @Parameter(names = {"--checkpoint"}, description = "Resume Delta Streamer from this checkpoint.")
    public String checkpoint = null; // 设置恢复点

    @Parameter(names = {"--initial-checkpoint-provider"}, description = "subclass of "
        + "org.apache.hudi.utilities.checkpointing.InitialCheckpointProvider. Generate check point for delta streamer "
        + "for the first run. This field will override the checkpoint of last commit using the checkpoint field. "
        + "Use this field only when switching source, for example, from DFS source to Kafka Source.")
    public String initialCheckpointProvider = null;

    @Parameter(names = {"--run-bootstrap"}, description = "Run bootstrap if bootstrap index is not found")
    public Boolean runBootstrap = false;

    @Parameter(names = {"--bootstrap-index-class"}, description = "subclass of BootstrapIndex")
    public String bootstrapIndexClass = HFileBootstrapIndex.class.getName();

    @Parameter(names = {"--help", "-h"}, help = true)
    public Boolean help = false;

    public boolean isAsyncCompactionEnabled() {
      return continuousMode && !forceDisableCompaction
          && HoodieTableType.MERGE_ON_READ.equals(HoodieTableType.valueOf(tableType));
    }

    public boolean isInlineCompactionEnabled() {
      return !continuousMode && !forceDisableCompaction
          && HoodieTableType.MERGE_ON_READ.equals(HoodieTableType.valueOf(tableType));
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Config config = (Config) o;
      return sourceLimit == config.sourceLimit
              && Objects.equals(targetBasePath, config.targetBasePath)
              && Objects.equals(targetTableName, config.targetTableName)
              && Objects.equals(tableType, config.tableType)
              && Objects.equals(baseFileFormat, config.baseFileFormat)
              && Objects.equals(propsFilePath, config.propsFilePath)
              && Objects.equals(configs, config.configs)
              && Objects.equals(sourceClassName, config.sourceClassName)
              && Objects.equals(sourceOrderingField, config.sourceOrderingField)
              && Objects.equals(payloadClassName, config.payloadClassName)
              && Objects.equals(schemaProviderClassName, config.schemaProviderClassName)
              && Objects.equals(transformerClassNames, config.transformerClassNames)
              && operation == config.operation
              && Objects.equals(filterDupes, config.filterDupes)
              && Objects.equals(enableHiveSync, config.enableHiveSync)
              && Objects.equals(maxPendingCompactions, config.maxPendingCompactions)
              && Objects.equals(continuousMode, config.continuousMode)
              && Objects.equals(minSyncIntervalSeconds, config.minSyncIntervalSeconds)
              && Objects.equals(sparkMaster, config.sparkMaster)
              && Objects.equals(commitOnErrors, config.commitOnErrors)
              && Objects.equals(deltaSyncSchedulingWeight, config.deltaSyncSchedulingWeight)
              && Objects.equals(compactSchedulingWeight, config.compactSchedulingWeight)
              && Objects.equals(deltaSyncSchedulingMinShare, config.deltaSyncSchedulingMinShare)
              && Objects.equals(compactSchedulingMinShare, config.compactSchedulingMinShare)
              && Objects.equals(forceDisableCompaction, config.forceDisableCompaction)
              && Objects.equals(checkpoint, config.checkpoint)
              && Objects.equals(initialCheckpointProvider, config.initialCheckpointProvider)
              && Objects.equals(help, config.help);
    }

    @Override
    public int hashCode() {
      return Objects.hash(targetBasePath, targetTableName, tableType,
              baseFileFormat, propsFilePath, configs, sourceClassName,
              sourceOrderingField, payloadClassName, schemaProviderClassName,
              transformerClassNames, sourceLimit, operation, filterDupes,
              enableHiveSync, maxPendingCompactions, continuousMode,
              minSyncIntervalSeconds, sparkMaster, commitOnErrors,
              deltaSyncSchedulingWeight, compactSchedulingWeight, deltaSyncSchedulingMinShare,
              compactSchedulingMinShare, forceDisableCompaction, checkpoint,
              initialCheckpointProvider, help);
    }
  
    @Override
    public String toString() {
      return "Config{"
              + "targetBasePath='" + targetBasePath + '\''
              + ", targetTableName='" + targetTableName + '\''
              + ", tableType='" + tableType + '\''
              + ", baseFileFormat='" + baseFileFormat + '\''
              + ", propsFilePath='" + propsFilePath + '\''
              + ", configs=" + configs
              + ", sourceClassName='" + sourceClassName + '\''
              + ", sourceOrderingField='" + sourceOrderingField + '\''
              + ", payloadClassName='" + payloadClassName + '\''
              + ", schemaProviderClassName='" + schemaProviderClassName + '\''
              + ", transformerClassNames=" + transformerClassNames
              + ", sourceLimit=" + sourceLimit
              + ", operation=" + operation
              + ", filterDupes=" + filterDupes
              + ", enableHiveSync=" + enableHiveSync
              + ", maxPendingCompactions=" + maxPendingCompactions
              + ", continuousMode=" + continuousMode
              + ", minSyncIntervalSeconds=" + minSyncIntervalSeconds
              + ", sparkMaster='" + sparkMaster + '\''
              + ", commitOnErrors=" + commitOnErrors
              + ", deltaSyncSchedulingWeight=" + deltaSyncSchedulingWeight
              + ", compactSchedulingWeight=" + compactSchedulingWeight
              + ", deltaSyncSchedulingMinShare=" + deltaSyncSchedulingMinShare
              + ", compactSchedulingMinShare=" + compactSchedulingMinShare
              + ", forceDisableCompaction=" + forceDisableCompaction
              + ", checkpoint='" + checkpoint + '\''
              + ", initialCheckpointProvider='" + initialCheckpointProvider + '\''
              + ", help=" + help
              + '}';
    }
  }

  public static final Config getConfig(String[] args) {
    Config cfg = new Config();
    JCommander cmd = new JCommander(cfg, null, args);
    if (cfg.help || args.length == 0) {
      cmd.usage();
      System.exit(1);
    }
    return cfg;
  }

  // 程序主入口
  public static void main(String[] args) throws Exception {
    // 1.首先拿到用户的配置项
    final Config cfg = getConfig(args);
    // 2.获取spark调度模式 FAIR/FIFO,当未启用YARN做资源管理时默认为FAIR(公平调度)
    Map<String, String> additionalSparkConfigs = SchedulerConfGenerator.getSparkSchedulingConfigs(cfg);
    // 3.创建spark上下文对象
    JavaSparkContext jssc =
        UtilHelpers.buildSparkContext("delta-streamer-" + cfg.targetTableName, cfg.sparkMaster, additionalSparkConfigs);

    if (cfg.enableHiveSync) {
      LOG.warn("--enable-hive-sync will be deprecated in a future release; please use --enable-sync instead for Hive syncing");
    }

    try {
      // 创建HoodieDeltaStreamer进行摄取数据
      //
      new HoodieDeltaStreamer(cfg, jssc).sync();
    } finally {
      // 5.最后都会停止
      jssc.stop();
    }
  }

  /**
   * Syncs data either in single-run or in continuous mode.
   */
  public static class DeltaSyncService extends HoodieAsyncService {

    private static final long serialVersionUID = 1L;
    /**
     * Delta Sync Config.
     */
    private final HoodieDeltaStreamer.Config cfg;

    /**
     * Schema provider that supplies the command for reading the input and writing out the target table.
     */
    private transient SchemaProvider schemaProvider;

    /**
     * Spark Session.
     */
    private transient SparkSession sparkSession;

    /**
     * Spark context.
     */
    private transient JavaSparkContext jssc;

    /**
     * Bag of properties with source, hoodie client, key generator etc.
     */
    TypedProperties props;

    /**
     * Async Compactor Service.
     */
    private Option<AsyncCompactService> asyncCompactService;

    /**
     * Table Type.
     */
    private final HoodieTableType tableType;

    /**
     * Delta Sync.
     */
    private transient DeltaSync deltaSync;

    public DeltaSyncService(Config cfg, JavaSparkContext jssc, FileSystem fs, Configuration conf,
                            Option<TypedProperties> properties) throws IOException {
      this.cfg = cfg;
      this.jssc = jssc;
      this.sparkSession = SparkSession.builder().config(jssc.getConf()).getOrCreate();
      this.asyncCompactService = Option.empty();

      if (fs.exists(new Path(cfg.targetBasePath))) {
        HoodieTableMetaClient meta =
            HoodieTableMetaClient.builder().setConf(new Configuration(fs.getConf())).setBasePath(cfg.targetBasePath).setLoadActiveTimelineOnLoad(false).build();
        tableType = meta.getTableType();
        // This will guarantee there is no surprise with table type
        ValidationUtils.checkArgument(tableType.equals(HoodieTableType.valueOf(cfg.tableType)),
            "Hoodie table is of type " + tableType + " but passed in CLI argument is " + cfg.tableType);

        // Load base file format
        // This will guarantee there is no surprise with base file type
        String baseFileFormat = meta.getTableConfig().getBaseFileFormat().toString();
        ValidationUtils.checkArgument(baseFileFormat.equals(cfg.baseFileFormat) || cfg.baseFileFormat == null,
            "Hoodie table's base file format is of type " + baseFileFormat + " but passed in CLI argument is "
                + cfg.baseFileFormat);
        cfg.baseFileFormat = meta.getTableConfig().getBaseFileFormat().toString();
        this.cfg.baseFileFormat = cfg.baseFileFormat;
      } else {
        tableType = HoodieTableType.valueOf(cfg.tableType);
        if (cfg.baseFileFormat == null) {
          cfg.baseFileFormat = "PARQUET"; // default for backward compatibility
        }
      }

      ValidationUtils.checkArgument(!cfg.filterDupes || cfg.operation != WriteOperationType.UPSERT,
          "'--filter-dupes' needs to be disabled when '--op' is 'UPSERT' to ensure updates are not missed.");

      this.props = properties.get();
      LOG.info("Creating delta streamer with configs : " + props.toString());
      this.schemaProvider = UtilHelpers.wrapSchemaProviderWithPostProcessor(
          UtilHelpers.createSchemaProvider(cfg.schemaProviderClassName, props, jssc), props, jssc, cfg.transformerClassNames);

      deltaSync = new DeltaSync(cfg, sparkSession, schemaProvider, props, jssc, fs, conf,
          this::onInitializingWriteClient);
    }

    public DeltaSyncService(HoodieDeltaStreamer.Config cfg, JavaSparkContext jssc, FileSystem fs, Configuration conf)
        throws IOException {
      this(cfg, jssc, fs, conf, Option.empty());
    }

    public DeltaSync getDeltaSync() {
      return deltaSync;
    }

    @Override
    protected Pair<CompletableFuture, ExecutorService> startService() {
      ExecutorService executor = Executors.newFixedThreadPool(1);
      return Pair.of(CompletableFuture.supplyAsync(() -> {
        boolean error = false;
        if (cfg.isAsyncCompactionEnabled()) {
          // set Scheduler Pool.
          LOG.info("Setting Spark Pool name for delta-sync to " + DELTASYNC_POOL_NAME);
          jssc.setLocalProperty("spark.scheduler.pool", DELTASYNC_POOL_NAME);
        }
        try {
          while (!isShutdownRequested()) {
            try {
              long start = System.currentTimeMillis();
              Option<Pair<Option<String>, JavaRDD<WriteStatus>>> scheduledCompactionInstantAndRDD = Option.ofNullable(deltaSync.syncOnce());
              if (scheduledCompactionInstantAndRDD.isPresent() && scheduledCompactionInstantAndRDD.get().getLeft().isPresent()) {
                LOG.info("Enqueuing new pending compaction instant (" + scheduledCompactionInstantAndRDD.get().getLeft() + ")");
                asyncCompactService.get().enqueuePendingCompaction(new HoodieInstant(State.REQUESTED,
                    HoodieTimeline.COMPACTION_ACTION, scheduledCompactionInstantAndRDD.get().getLeft().get()));
                asyncCompactService.get().waitTillPendingCompactionsReducesTo(cfg.maxPendingCompactions);
              }
              long toSleepMs = cfg.minSyncIntervalSeconds * 1000 - (System.currentTimeMillis() - start);
              if (toSleepMs > 0) {
                LOG.info("Last sync ran less than min sync interval: " + cfg.minSyncIntervalSeconds + " s, sleep: "
                    + toSleepMs + " ms.");
                Thread.sleep(toSleepMs);
              }
            } catch (Exception e) {
              LOG.error("Shutting down delta-sync due to exception", e);
              error = true;
              throw new HoodieException(e.getMessage(), e);
            }
          }
        } finally {
          shutdownCompactor(error);
        }
        return true;
      }, executor), executor);
    }

    /**
     * Shutdown compactor as DeltaSync is shutdown.
     */
    private void shutdownCompactor(boolean error) {
      LOG.info("Delta Sync shutdown. Error ?" + error);
      if (asyncCompactService.isPresent()) {
        LOG.warn("Gracefully shutting down compactor");
        asyncCompactService.get().shutdown(false);
      }
    }

    /**
     * Callback to initialize write client and start compaction service if required.
     *
     * @param writeClient HoodieWriteClient
     * @return
     */
    protected Boolean onInitializingWriteClient(SparkRDDWriteClient writeClient) {
      if (cfg.isAsyncCompactionEnabled()) {
        if (asyncCompactService.isPresent()) {
          // Update the write client used by Async Compactor.
          asyncCompactService.get().updateWriteClient(writeClient);
        } else {
          asyncCompactService = Option.ofNullable(new SparkAsyncCompactService(new HoodieSparkEngineContext(jssc), writeClient));
          // Enqueue existing pending compactions first
          HoodieTableMetaClient meta =
              HoodieTableMetaClient.builder().setConf(new Configuration(jssc.hadoopConfiguration())).setBasePath(cfg.targetBasePath).setLoadActiveTimelineOnLoad(true).build();
          List<HoodieInstant> pending = CompactionUtils.getPendingCompactionInstantTimes(meta);
          pending.forEach(hoodieInstant -> asyncCompactService.get().enqueuePendingCompaction(hoodieInstant));
          asyncCompactService.get().start((error) -> {
            // Shutdown DeltaSync
            shutdown(false);
            return true;
          });
          try {
            asyncCompactService.get().waitTillPendingCompactionsReducesTo(cfg.maxPendingCompactions);
          } catch (InterruptedException ie) {
            throw new HoodieException(ie);
          }
        }
      }
      return true;
    }

    /**
     * Close all resources.
     */
    public void close() {
      if (null != deltaSync) {
        deltaSync.close();
      }
    }

    public SchemaProvider getSchemaProvider() {
      return schemaProvider;
    }

    public SparkSession getSparkSession() {
      return sparkSession;
    }

    public TypedProperties getProps() {
      return props;
    }
  }

  public DeltaSyncService getDeltaSyncService() {
    return deltaSyncService.get();
  }
}
