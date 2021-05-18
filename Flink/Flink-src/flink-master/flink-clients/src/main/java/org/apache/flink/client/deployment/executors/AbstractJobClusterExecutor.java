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

package org.apache.flink.client.deployment.executors;

import org.apache.flink.annotation.Internal;
import org.apache.flink.api.dag.Pipeline;
import org.apache.flink.client.cli.ExecutionConfigAccessor;
import org.apache.flink.client.deployment.ClusterClientFactory;
import org.apache.flink.client.deployment.ClusterClientJobClientAdapter;
import org.apache.flink.client.deployment.ClusterDescriptor;
import org.apache.flink.client.deployment.ClusterSpecification;
import org.apache.flink.client.program.ClusterClientProvider;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.execution.JobClient;
import org.apache.flink.core.execution.PipelineExecutor;
import org.apache.flink.runtime.jobgraph.JobGraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import java.util.concurrent.CompletableFuture;

import static org.apache.flink.util.Preconditions.checkNotNull;

/**
 * An abstract {@link PipelineExecutor} used to execute {@link Pipeline pipelines} on dedicated
 * (per-job) clusters.
 *
 * @param <ClusterID> the type of the id of the cluster.
 * @param <ClientFactory> the type of the {@link ClusterClientFactory} used to create/retrieve a
 *     client to the target cluster.
 */
@Internal
public class AbstractJobClusterExecutor<
                ClusterID, ClientFactory extends ClusterClientFactory<ClusterID>>
        implements PipelineExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractJobClusterExecutor.class);

    private final ClientFactory clusterClientFactory;

    public AbstractJobClusterExecutor(@Nonnull final ClientFactory clusterClientFactory) {
        this.clusterClientFactory = checkNotNull(clusterClientFactory);
    }

    @Override
    public CompletableFuture<JobClient> execute(
            @Nonnull final Pipeline pipeline,
            @Nonnull final Configuration configuration,
            @Nonnull final ClassLoader userCodeClassloader)
            throws Exception {
        // 1.将streamGraph转换成jobGraph
            // StreamGraph 转变成 JobGraph过程：
            // StreamNode 转成 JobVertex。
            // StreamEdge 转成 JobEdge。
            // JobEdge 和 JobVertex 之间创建 IntermediateDataSet 来连接。
        final JobGraph jobGraph = PipelineExecutorUtils.getJobGraph(pipeline, configuration);
        // 2.创建集群描述器，其实也就是创建YarnClient、初始化YarnClient以及启动一个YarnClient
        try (final ClusterDescriptor<ClusterID> clusterDescriptor =
                clusterClientFactory.createClusterDescriptor(configuration)) {
            final ExecutionConfigAccessor configAccessor =
                    ExecutionConfigAccessor.fromConfiguration(configuration);
        // 3.创建集群资源配置描述器，其实也就是获取相关资源分配 JM内存、TM内存、每个TM所能使用的slot数量
            final ClusterSpecification clusterSpecification =
                    clusterClientFactory.getClusterSpecification(configuration);
        // 4.部署JobCluster
            final ClusterClientProvider<ClusterID> clusterClientProvider =
                    clusterDescriptor.deployJobCluster(
                            clusterSpecification, jobGraph, configAccessor.getDetachedMode());
            LOG.info("Job has been submitted with JobID " + jobGraph.getJobID());

            return CompletableFuture.completedFuture(
                    new ClusterClientJobClientAdapter<>(
                            clusterClientProvider, jobGraph.getJobID(), userCodeClassloader));
        }
    }
}
