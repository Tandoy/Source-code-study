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

package org.apache.hudi.table.action.cluster;

import org.apache.hudi.avro.model.HoodieClusteringPlan;
import org.apache.hudi.avro.model.HoodieRequestedReplaceMetadata;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.model.WriteOperationType;
import org.apache.hudi.common.table.timeline.HoodieInstant;
import org.apache.hudi.common.table.timeline.HoodieTimeline;
import org.apache.hudi.common.table.timeline.TimelineMetadataUtils;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.exception.HoodieIOException;
import org.apache.hudi.table.HoodieTable;
import org.apache.hudi.table.action.BaseActionExecutor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class BaseClusteringPlanActionExecutor<T extends HoodieRecordPayload, I, K, O> extends BaseActionExecutor<T, I, K, O, Option<HoodieClusteringPlan>> {

  private final Option<Map<String, String>> extraMetadata;
  private static final Logger LOG = LogManager.getLogger(BaseClusteringPlanActionExecutor.class);

  public BaseClusteringPlanActionExecutor(HoodieEngineContext context,
                                          HoodieWriteConfig config,
                                          HoodieTable<T, I, K, O> table,
                                          String instantTime,
                                          Option<Map<String, String>> extraMetadata) {
    super(context, config, table, instantTime);
    this.extraMetadata = extraMetadata;
  }

  protected abstract Option<HoodieClusteringPlan> createClusteringPlan();

  @Override
  public Option<HoodieClusteringPlan> execute() {
    // 1.创建ClusteringPlan，注意这里会对当前要Clustering的表.commit进行计数，默认要大于4个
    Option<HoodieClusteringPlan> planOption = createClusteringPlan();
    if (planOption.isPresent()) {
      // TODO The log shows that Clustering is successful, but only the replacecommit.requested file is generated but the .replacecommit file is not generated
      // 此处clusteringInstant会写入元数据文件中 创建20210705105809.replacecommit.requested文件
      HoodieInstant clusteringInstant =
          new HoodieInstant(HoodieInstant.State.REQUESTED, HoodieTimeline.REPLACE_COMMIT_ACTION, instantTime);
      try {
        HoodieRequestedReplaceMetadata requestedReplaceMetadata = HoodieRequestedReplaceMetadata.newBuilder()
            .setOperationType(WriteOperationType.CLUSTER.name())
            .setExtraMetadata(extraMetadata.orElse(Collections.emptyMap()))
            .setClusteringPlan(planOption.get())
            .build();
        table.getActiveTimeline().saveToPendingReplaceCommit(clusteringInstant,
            TimelineMetadataUtils.serializeRequestedReplaceMetadata(requestedReplaceMetadata));
      } catch (IOException ioe) {
        throw new HoodieIOException("Exception scheduling clustering", ioe);
      }
    }
    LOG.info("*************************only the replacecommit.requested file is generated but the .replacecommit file is not generated**********************************");
    return planOption;
  }
}
