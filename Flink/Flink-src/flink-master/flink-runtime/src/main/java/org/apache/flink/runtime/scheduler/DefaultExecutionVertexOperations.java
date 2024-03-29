/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.flink.runtime.scheduler;

import org.apache.flink.runtime.JobException;
import org.apache.flink.runtime.executiongraph.ExecutionVertex;

import java.util.concurrent.CompletableFuture;

class DefaultExecutionVertexOperations implements ExecutionVertexOperations {

    @Override
    public void deploy(final ExecutionVertex executionVertex) throws JobException {
        // executionVertex自己开始部署
        executionVertex.deploy();
    }

    @Override
    public CompletableFuture<?> cancel(final ExecutionVertex executionVertex) {
        return executionVertex.cancel();
    }

    @Override
    public void markFailed(final ExecutionVertex executionVertex, final Throwable cause) {
        executionVertex.markFailed(cause);
    }
}
