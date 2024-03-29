/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.taskexecutor.rpc;

import org.apache.flink.api.common.JobID;
import org.apache.flink.runtime.checkpoint.CheckpointCoordinatorGateway;
import org.apache.flink.runtime.checkpoint.CheckpointException;
import org.apache.flink.runtime.checkpoint.CheckpointMetrics;
import org.apache.flink.runtime.checkpoint.TaskStateSnapshot;
import org.apache.flink.runtime.executiongraph.ExecutionAttemptID;
import org.apache.flink.runtime.messages.checkpoint.DeclineCheckpoint;
import org.apache.flink.runtime.taskmanager.CheckpointResponder;
import org.apache.flink.util.Preconditions;

/**
 * RpcCheckpointResponder 作为 CheckpointResponder 的具体实现，主要是通过 RPC 调用通知 CheckpointCoordinatorGateway，
 * 即通知给 JobMaster, JobMaster 调用 CheckpointCoordinator.receiveAcknowledgeMessage()
 * 和 CheckpointCoordinator.receiveDeclineMessage() 进行处理。
 */
public class RpcCheckpointResponder implements CheckpointResponder {

    private final CheckpointCoordinatorGateway checkpointCoordinatorGateway;

    public RpcCheckpointResponder(CheckpointCoordinatorGateway checkpointCoordinatorGateway) {
        this.checkpointCoordinatorGateway =
                Preconditions.checkNotNull(checkpointCoordinatorGateway);
    }

    @Override
    public void acknowledgeCheckpoint(
            JobID jobID,
            ExecutionAttemptID executionAttemptID,
            long checkpointId,
            CheckpointMetrics checkpointMetrics,
            TaskStateSnapshot subtaskState) {

        checkpointCoordinatorGateway.acknowledgeCheckpoint(
                jobID, executionAttemptID, checkpointId, checkpointMetrics, subtaskState);
    }

    @Override
    public void reportCheckpointMetrics(
            JobID jobID,
            ExecutionAttemptID executionAttemptID,
            long checkpointId,
            CheckpointMetrics checkpointMetrics) {
        checkpointCoordinatorGateway.reportCheckpointMetrics(
                jobID, executionAttemptID, checkpointId, checkpointMetrics);
    }

    @Override
    public void declineCheckpoint(
            JobID jobID,
            ExecutionAttemptID executionAttemptID,
            long checkpointId,
            CheckpointException checkpointException) {

        checkpointCoordinatorGateway.declineCheckpoint(
                new DeclineCheckpoint(
                        jobID, executionAttemptID, checkpointId, checkpointException));
    }
}
