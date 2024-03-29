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

package org.apache.hudi.table.action.clean;

import java.util.List;
import java.util.Map;

import org.apache.hudi.avro.model.HoodieCleanerPlan;
import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.table.HoodieTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JavaScheduleCleanActionExecutor<T extends HoodieRecordPayload> extends
    BaseCleanPlanActionExecutor<T, List<HoodieRecord<T>>, List<HoodieKey>, List<WriteStatus>> {

  private static final Logger LOG = LogManager.getLogger(JavaScheduleCleanActionExecutor.class);

  public JavaScheduleCleanActionExecutor(HoodieEngineContext context,
                                  HoodieWriteConfig config,
                                  HoodieTable<T, List<HoodieRecord<T>>, List<HoodieKey>, List<WriteStatus>> table,
                                  String instantTime,
                                  Option<Map<String, String>> extraMetadata) {
    super(context, config, table, instantTime, extraMetadata);
  }

  @Override
  protected Option<HoodieCleanerPlan> createCleanerPlan() {
    return super.execute();
  }
}
