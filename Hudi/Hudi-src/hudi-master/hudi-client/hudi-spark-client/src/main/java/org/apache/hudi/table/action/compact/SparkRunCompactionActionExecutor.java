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

package org.apache.hudi.table.action.compact;

import org.apache.hudi.avro.model.HoodieCompactionPlan;
import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.client.common.HoodieSparkEngineContext;
import org.apache.hudi.client.utils.SparkMemoryUtils;
import org.apache.hudi.common.model.HoodieCommitMetadata;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.model.HoodieWriteStat;
import org.apache.hudi.common.table.timeline.HoodieActiveTimeline;
import org.apache.hudi.common.table.timeline.HoodieInstant;
import org.apache.hudi.common.table.timeline.HoodieTimeline;
import org.apache.hudi.common.util.CompactionUtils;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.exception.HoodieCompactionException;
import org.apache.hudi.table.HoodieTable;
import org.apache.hudi.table.action.BaseActionExecutor;
import org.apache.hudi.table.action.HoodieWriteMetadata;

import org.apache.spark.api.java.JavaRDD;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("checkstyle:LineLength")
public class SparkRunCompactionActionExecutor<T extends HoodieRecordPayload> extends
    BaseActionExecutor<T, JavaRDD<HoodieRecord<T>>, JavaRDD<HoodieKey>, JavaRDD<WriteStatus>, HoodieWriteMetadata<JavaRDD<WriteStatus>>> {

  public SparkRunCompactionActionExecutor(HoodieSparkEngineContext context,
                                          HoodieWriteConfig config,
                                          HoodieTable<T, JavaRDD<HoodieRecord<T>>, JavaRDD<HoodieKey>, JavaRDD<WriteStatus>> table,
                                          String instantTime) {
    super(context, config, table, instantTime);
  }

  @Override
  public HoodieWriteMetadata<JavaRDD<WriteStatus>> execute() {
    // 1.获取需要compaction的instant
    HoodieInstant instant = HoodieTimeline.getCompactionRequestedInstant(instantTime);
    HoodieTimeline pendingCompactionTimeline = table.getActiveTimeline().filterPendingCompactionTimeline();
    // 2.进行compaction的instant相关检查
    if (!pendingCompactionTimeline.containsInstant(instant)) {
      throw new IllegalStateException(
          "No Compaction request available at " + instantTime + " to run compaction");
    }
    // 3.创建compaction元数据
    HoodieWriteMetadata<JavaRDD<WriteStatus>> compactionMetadata = new HoodieWriteMetadata<>();
    try {
      HoodieActiveTimeline timeline = table.getActiveTimeline();
      // 4.生成compact执行计划
      HoodieCompactionPlan compactionPlan =
          CompactionUtils.getCompactionPlan(table.getMetaClient(), instantTime);
      // Mark instant as compaction inflight
      // 5.先进行一些检查，1. 如果有inflight状态的写入，那么最早的instant的时间一定大于正在进行压缩的时间；2. commit、deltacommit、compaction类型的instant的时间一定小于正在进行压缩的时间
      timeline.transitionCompactionRequestedToInflight(instant);
      table.getMetaClient().reloadActiveTimeline();

      HoodieSparkMergeOnReadTableCompactor compactor = new HoodieSparkMergeOnReadTableCompactor();
      // 6.进行compact
      JavaRDD<WriteStatus> statuses = compactor.compact(context, compactionPlan, table, config, instantTime);

      statuses.persist(SparkMemoryUtils.getWriteStatusStorageLevel(config.getProps()));
      context.setJobStatus(this.getClass().getSimpleName(), "Preparing compaction metadata");
      List<HoodieWriteStat> updateStatusMap = statuses.map(WriteStatus::getStat).collect();
      HoodieCommitMetadata metadata = new HoodieCommitMetadata(true);
      for (HoodieWriteStat stat : updateStatusMap) {
        metadata.addWriteStat(stat.getPartitionPath(), stat);
      }
      metadata.addMetadata(HoodieCommitMetadata.SCHEMA_KEY, config.getSchema());

      compactionMetadata.setWriteStatuses(statuses);
      compactionMetadata.setCommitted(false);
      compactionMetadata.setCommitMetadata(Option.of(metadata));
    } catch (IOException e) {
      throw new HoodieCompactionException("Could not compact " + config.getBasePath(), e);
    }

    return compactionMetadata;
  }
}
