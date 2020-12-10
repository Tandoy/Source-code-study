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

package org.apache.spark.scheduler

import java.lang.management.ManagementFactory
import java.nio.ByteBuffer
import java.util.Properties

import scala.language.existentials

import org.apache.spark._
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.internal.Logging
import org.apache.spark.rdd.RDD
import org.apache.spark.shuffle.ShuffleWriter

/**
 * A ShuffleMapTask divides the elements of an RDD into multiple buckets (based on a partitioner
 * specified in the ShuffleDependency).
 *
 * See [[org.apache.spark.scheduler.Task]] for more information.
 *
 * @param stageId id of the stage this task belongs to
 * @param stageAttemptId attempt id of the stage this task belongs to
 * @param taskBinary broadcast version of the RDD and the ShuffleDependency. Once deserialized,
 *                   the type should be (RDD[_], ShuffleDependency[_, _, _]).
 * @param partition partition of the RDD this task is associated with
 * @param locs preferred task execution locations for locality scheduling
 * @param localProperties copy of thread-local properties set by the user on the driver side.
 * @param serializedTaskMetrics a `TaskMetrics` that is created and serialized on the driver side
 *                              and sent to executor side.
 *
 * The parameters below are optional:
 * @param jobId id of the job this task belongs to
 * @param appId id of the app this task belongs to
 * @param appAttemptId attempt id of the app this task belongs to
 * @param isBarrier whether this task belongs to a barrier stage. Spark must launch all the tasks
 *                  at the same time for a barrier stage.
 */
private[spark] class ShuffleMapTask(
    stageId: Int,
    stageAttemptId: Int,
    taskBinary: Broadcast[Array[Byte]],
    partition: Partition,
    @transient private var locs: Seq[TaskLocation],
    localProperties: Properties,
    serializedTaskMetrics: Array[Byte],
    jobId: Option[Int] = None,
    appId: Option[String] = None,
    appAttemptId: Option[String] = None,
    isBarrier: Boolean = false)
  extends Task[MapStatus](stageId, stageAttemptId, partition.index, localProperties,
    serializedTaskMetrics, jobId, appId, appAttemptId, isBarrier)
  with Logging {

  /** A constructor used only in test suites. This does not require passing in an RDD. */
  def this(partitionId: Int) {
    this(0, 0, null, new Partition { override def index: Int = 0 }, null, new Properties, null)
  }

  @transient private val preferredLocs: Seq[TaskLocation] = {
    if (locs == null) Nil else locs.toSet.toSeq
  }
  //继承至Task 返回MapStatus（计算后数据rdd）
  override def runTask(context: TaskContext): MapStatus = {
    // Deserialize the RDD using the broadcast variable.
    val threadMXBean = ManagementFactory.getThreadMXBean
    val deserializeStartTime = System.currentTimeMillis()
    val deserializeStartCpuTime = if (threadMXBean.isCurrentThreadCpuTimeSupported) {
      threadMXBean.getCurrentThreadCpuTime
    } else 0L
    val ser = SparkEnv.get.closureSerializer.newInstance()
    /**
     * 使用广播变量进行rdd的序列化
     */
    val (rdd, dep) = ser.deserialize[(RDD[_], ShuffleDependency[_, _, _])](
      ByteBuffer.wrap(taskBinary.value), Thread.currentThread.getContextClassLoader)
    _executorDeserializeTime = System.currentTimeMillis() - deserializeStartTime
    _executorDeserializeCpuTime = if (threadMXBean.isCurrentThreadCpuTimeSupported) {
      threadMXBean.getCurrentThreadCpuTime - deserializeStartCpuTime
    } else 0L

    var writer: ShuffleWriter[Any, Any] = null
    try {
      val manager = SparkEnv.get.shuffleManager
      //shuffleManager --> shuffleWriter
      writer = manager.getWriter[Any, Any](dep.shuffleHandle, partitionId, context)
      //这里即为task执行原理的重点：rdd.iterator
      //rdd进行迭代式计算partition自定义的算子和函数
      //实际上执行rdd的compute（）
      //底层调用相关rdd如MapPartitionRdd的compute方法进行计算
      //spark会对用户自定义的进行相关封装f
      //同时write方法也是Shuffle的主入口
      /**
       *假设有一个节点上面运行了4个 ShuffleMapTask，然后这个节点上只有2个 cpu core。假如有另外一台节点，上面也运行了4个ResultTask，现在呢，正等着要去
       *ShuffleMapTask 的输出数据来完成比如 reduceByKey 等操作。
       *每个 ShuffleMapTask 都会为 ReduceTask 创建一份 bucket 缓存，以及对应的 ShuffleBlockFile 磁盘文件。
       *ShuffleMapTask 的输出会作为 MapStatus，发送到 DAGScheduler 的 MapOutputTrackerMaster 中。MapStatus 包含了每个 ResultTask 要拉取的数据的大小。
       *每个 ResultTask 会用 BlockStoreShuffleFetcher 去 MapOutputTrackerMaster 获取自己要拉取数据的信息，然后底层通过 BlockManager 将数据拉取过来。
       *每个 ResultTask 拉取过来的数据，其实就会组成一个内部的RDD，叫ShuffleRDD；优先放入内存，其次内存不够，那么写入磁盘。
       *然后每个ResultTask针对数据进行聚合，最后生成MapPartitionsRDD，也就是我们执行reduceByKey等操作希望获得的那个RDD。map端的数据，可以理解为Shuffle的第一个
       *RDD，MapPartitionsRDD。所以假设如果有100个map task ，100个 reduce task，本地磁盘要产生10000个文件，磁盘IO过多，影响性能。
       */
      writer.write(rdd.iterator(partition, context).asInstanceOf[Iterator[_ <: Product2[Any, Any]]])
      writer.stop(success = true).get
    } catch {
      case e: Exception =>
        try {
          if (writer != null) {
            writer.stop(success = false)
          }
        } catch {
          case e: Exception =>
            log.debug("Could not stop writer", e)
        }
        throw e
    }
  }

  override def preferredLocations: Seq[TaskLocation] = preferredLocs

  override def toString: String = "ShuffleMapTask(%d, %d)".format(stageId, partitionId)
}
