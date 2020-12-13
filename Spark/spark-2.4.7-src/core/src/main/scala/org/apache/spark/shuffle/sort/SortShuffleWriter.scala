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

package org.apache.spark.shuffle.sort

import org.apache.spark._
import org.apache.spark.internal.Logging
import org.apache.spark.scheduler.MapStatus
import org.apache.spark.shuffle.{BaseShuffleHandle, IndexShuffleBlockResolver, ShuffleWriter}
import org.apache.spark.storage.ShuffleBlockId
import org.apache.spark.util.Utils
import org.apache.spark.util.collection.ExternalSorter

private[spark] class SortShuffleWriter[K, V, C](
    shuffleBlockResolver: IndexShuffleBlockResolver,
    handle: BaseShuffleHandle[K, V, C],
    mapId: Int,
    context: TaskContext)
  extends ShuffleWriter[K, V] with Logging {

  private val dep = handle.dependency

  private val blockManager = SparkEnv.get.blockManager

  private var sorter: ExternalSorter[K, V, _] = null

  // Are we in the process of stopping? Because map tasks can call stop() with success = true
  // and then call stop() with success = false if they get an exception, we want to make sure
  // we don't try deleting files, etc twice.
  private var stopping = false

  private var mapStatus: MapStatus = null

  private val writeMetrics = context.taskMetrics().shuffleWriteMetrics

  /** Write a bunch of records to this task's output */
  override def write(records: Iterator[Product2[K, V]]): Unit = {
    sorter = if (dep.mapSideCombine) { //mapSideCombine 是否map端本地进行聚合 默认false
      //若设置为map端聚合，就会执行本地聚合，比如本地有(hello，1)、(hello，1)，那么此时就会聚合成(hello，2)
      //dep.keyOrdering 排序函数定义
      new ExternalSorter[K, V, C](
        context, dep.aggregator, Some(dep.partitioner), dep.keyOrdering, dep.serializer)
    } else {
      // In this case we pass neither an aggregator nor an ordering to the sorter, because we don't
      // care whether the keys get sorted in each partition; that will be done on the reduce side
      // if the operation being run is sortByKey.
      new ExternalSorter[K, V, V](
        context, aggregator = None, Some(dep.partitioner), ordering = None, dep.serializer)
    }
    //根据是否map端聚合创建不同的ExternalSorter
    // 根据排序方式，对数据进行排序并写入内存缓冲区。
    // 若排序中计算结果超出的阈值，
    // 则将其溢写到磁盘数据文件
    /**
     *1.没有聚合和排序，数据先按照partition写入不同的文件中，最后按partition顺序合并写入同一文件 。适合partition数量较少时。将多个bucket合并到同一文件，减少map输出文件数，节省磁盘I/O，提高性能。
     *2.没有聚合但有排序，在缓存对数据先根据分区（或者还有key）进行排序，最后按partition顺序合并写入同一文件。适合当partition数量较多时。将多个bucket合并到同一文件，减少map输出文件数，节省磁盘I/O，提高性能。缓存使用超过阈值，将数据写入磁盘。
     *3.有聚合有排序，现在缓存中根据key值聚合，再在缓存对数据先根据分区（或者还有key）进行排序，最后按partition顺序合并写入同一文件。将多个bucket合并到同一文件，减少map输出文件数，节省磁盘I/O，提高性能。缓存使用超过阈值，将数据写入磁盘。逐条的读取数据，并进行聚合，减少了内存的占用。
     */
    sorter.insertAll(records)

    // Don't bother including the time to open the merged output file in the shuffle write time,
    // because it just opens a single file, so is typically too fast to measure accurately
    // (see SPARK-3570).
    // 通过shuffleId、mapId获取shuffleMapTask输出的文件路径
    val output = shuffleBlockResolver.getDataFile(dep.shuffleId, mapId)
    // 创建删除临时文件
    val tmp = Utils.tempFileWith(output)
    try {
      // 根据shuffleId、mapId、reduceId获取blockId
      val blockId = ShuffleBlockId(dep.shuffleId, mapId, IndexShuffleBlockResolver.NOOP_REDUCE_ID)
      // 实际的写数据方法，返回所有分区id
      val partitionLengths = sorter.writePartitionedFile(blockId, tmp)
      shuffleBlockResolver.writeIndexFileAndCommit(dep.shuffleId, mapId, partitionLengths, tmp)
      // mapStatus：记录每个shuffleMapTask输出的数据信息，resultTask后续会根据mapStatus到指定机器上拉取对应的数据会本地
      mapStatus = MapStatus(blockManager.shuffleServerId, partitionLengths)
    } finally {
      if (tmp.exists() && !tmp.delete()) {
        logError(s"Error while deleting temp file ${tmp.getAbsolutePath}")
      }
    }
  }

  /** Close this writer, passing along whether the map completed */
  override def stop(success: Boolean): Option[MapStatus] = {
    try {
      if (stopping) {
        return None
      }
      stopping = true
      if (success) {
        return Option(mapStatus)
      } else {
        return None
      }
    } finally {
      // Clean up our sorter, which may have its own intermediate files
      if (sorter != null) {
        val startTime = System.nanoTime()
        sorter.stop()
        writeMetrics.incWriteTime(System.nanoTime - startTime)
        sorter = null
      }
    }
  }
}

private[spark] object SortShuffleWriter {
  def shouldBypassMergeSort(conf: SparkConf, dep: ShuffleDependency[_, _, _]): Boolean = {
    // We cannot bypass sorting if we need to do map-side aggregation.
    if (dep.mapSideCombine) {
      false
    } else {
      val bypassMergeThreshold: Int = conf.getInt("spark.shuffle.sort.bypassMergeThreshold", 200)
      dep.partitioner.numPartitions <= bypassMergeThreshold
    }
  }
}
