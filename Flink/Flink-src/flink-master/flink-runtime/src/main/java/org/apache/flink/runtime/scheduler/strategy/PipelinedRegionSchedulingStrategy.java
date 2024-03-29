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

package org.apache.flink.runtime.scheduler.strategy;

import org.apache.flink.runtime.execution.ExecutionState;
import org.apache.flink.runtime.jobgraph.IntermediateDataSetID;
import org.apache.flink.runtime.jobgraph.IntermediateResultPartitionID;
import org.apache.flink.runtime.scheduler.DeploymentOption;
import org.apache.flink.runtime.scheduler.ExecutionVertexDeploymentOption;
import org.apache.flink.runtime.scheduler.SchedulerOperations;
import org.apache.flink.util.IterableUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.flink.util.Preconditions.checkNotNull;
import static org.apache.flink.util.Preconditions.checkState;

/**
 * {@link SchedulingStrategy} instance which schedules tasks in granularity of pipelined regions.
 */
public class PipelinedRegionSchedulingStrategy implements SchedulingStrategy {

    private final SchedulerOperations schedulerOperations;

    private final SchedulingTopology schedulingTopology;

    private final DeploymentOption deploymentOption = new DeploymentOption(false);

    /** ConsumedPartitionGroups are correlated if they have the same result id. */
    private final Map<IntermediateDataSetID, Set<ConsumedPartitionGroup>>
            correlatedResultPartitionGroups = new HashMap<>();

    /** External consumer regions of each ConsumedPartitionGroup. */
    private final Map<ConsumedPartitionGroup, Set<SchedulingPipelinedRegion>>
            partitionGroupConsumerRegions = new IdentityHashMap<>();

    private final Map<SchedulingPipelinedRegion, List<ExecutionVertexID>> regionVerticesSorted =
            new IdentityHashMap<>();

    /** The ConsumedPartitionGroups which are produced by multiple regions. */
    private final Set<ConsumedPartitionGroup> crossRegionConsumedPartitionGroups =
            Collections.newSetFromMap(new IdentityHashMap<>());

    public PipelinedRegionSchedulingStrategy(
            final SchedulerOperations schedulerOperations,
            final SchedulingTopology schedulingTopology) {

        this.schedulerOperations = checkNotNull(schedulerOperations);
        this.schedulingTopology = checkNotNull(schedulingTopology);

        init();
    }

    private void init() {

        initCrossRegionConsumedPartitionGroups();

        initPartitionGroupConsumerRegions();

        initCorrelatedResultPartitionGroups();

        for (SchedulingExecutionVertex vertex : schedulingTopology.getVertices()) {
            final SchedulingPipelinedRegion region =
                    schedulingTopology.getPipelinedRegionOfVertex(vertex.getId());
            regionVerticesSorted
                    .computeIfAbsent(region, r -> new ArrayList<>())
                    .add(vertex.getId());
        }
    }

    private void initCrossRegionConsumedPartitionGroups() {
        Set<ConsumedPartitionGroup> visitedPartitionGroups =
                Collections.newSetFromMap(new IdentityHashMap<>());

        for (SchedulingPipelinedRegion pipelinedRegion :
                schedulingTopology.getAllPipelinedRegions()) {
            for (ConsumedPartitionGroup consumedPartitionGroup :
                    pipelinedRegion.getAllBlockingConsumedPartitionGroups()) {
                if (!visitedPartitionGroups.contains(consumedPartitionGroup)) {
                    visitedPartitionGroups.add(consumedPartitionGroup);

                    SchedulingPipelinedRegion producerRegion = null;
                    for (IntermediateResultPartitionID partitionId : consumedPartitionGroup) {
                        SchedulingPipelinedRegion region = getProducerRegion(partitionId);
                        if (producerRegion == null) {
                            producerRegion = region;
                        } else if (producerRegion != region) {
                            crossRegionConsumedPartitionGroups.add(consumedPartitionGroup);
                            break;
                        }
                    }
                }
            }
        }
    }

    private SchedulingPipelinedRegion getProducerRegion(IntermediateResultPartitionID partitionId) {
        return schedulingTopology.getPipelinedRegionOfVertex(
                schedulingTopology.getResultPartition(partitionId).getProducer().getId());
    }

    private void initPartitionGroupConsumerRegions() {
        for (SchedulingPipelinedRegion region : schedulingTopology.getAllPipelinedRegions()) {
            for (ConsumedPartitionGroup consumedPartitionGroup :
                    region.getAllBlockingConsumedPartitionGroups()) {
                if (crossRegionConsumedPartitionGroups.contains(consumedPartitionGroup)
                        || isExternalConsumedPartitionGroup(consumedPartitionGroup, region)) {
                    partitionGroupConsumerRegions
                            .computeIfAbsent(consumedPartitionGroup, group -> new HashSet<>())
                            .add(region);
                }
            }
        }
    }

    private void initCorrelatedResultPartitionGroups() {
        for (ConsumedPartitionGroup consumedPartitionGroup :
                partitionGroupConsumerRegions.keySet()) {
            for (IntermediateResultPartitionID partitionId : consumedPartitionGroup) {
                correlatedResultPartitionGroups
                        .computeIfAbsent(
                                partitionId.getIntermediateDataSetID(), id -> new HashSet<>())
                        .add(consumedPartitionGroup);
            }
        }
    }

    // 开始调度
    @Override
    public void startScheduling() {
        // 1.拿到所有划分的局部
        final Set<SchedulingPipelinedRegion> sourceRegions =
                IterableUtils.toStream(schedulingTopology.getAllPipelinedRegions())
                        .filter(this::isSourceRegion)
                        .collect(Collectors.toSet());
        // 调度
        maybeScheduleRegions(sourceRegions);
    }

    private boolean isSourceRegion(SchedulingPipelinedRegion region) {
        for (ConsumedPartitionGroup consumedPartitionGroup :
                region.getAllBlockingConsumedPartitionGroups()) {
            if (crossRegionConsumedPartitionGroups.contains(consumedPartitionGroup)
                    || isExternalConsumedPartitionGroup(consumedPartitionGroup, region)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void restartTasks(final Set<ExecutionVertexID> verticesToRestart) {
        final Set<SchedulingPipelinedRegion> regionsToRestart =
                verticesToRestart.stream()
                        .map(schedulingTopology::getPipelinedRegionOfVertex)
                        .collect(Collectors.toSet());
        maybeScheduleRegions(regionsToRestart);
    }

    @Override
    public void onExecutionStateChange(
            final ExecutionVertexID executionVertexId, final ExecutionState executionState) {
        if (executionState == ExecutionState.FINISHED) {
            final Set<ConsumedPartitionGroup> finishedConsumedPartitionGroups =
                    IterableUtils.toStream(
                                    schedulingTopology
                                            .getVertex(executionVertexId)
                                            .getProducedResults())
                            .filter(
                                    partition ->
                                            partition.getState() == ResultPartitionState.CONSUMABLE)
                            .flatMap(
                                    partition ->
                                            correlatedResultPartitionGroups
                                                    .getOrDefault(
                                                            partition.getResultId(),
                                                            Collections.emptySet())
                                                    .stream())
                            .collect(Collectors.toSet());

            // for POINTWISE consumers of a BLOCKING partition, it's possible that some of the
            // consumers are not affected by the restarting of sibling vertices so they are still in
            // SCHEDULED/DEPLOYING/RUNNING/FINISHED. We should skip rescheduling these vertices.
            final Set<SchedulingPipelinedRegion> consumerRegions =
                    finishedConsumedPartitionGroups.stream()
                            .flatMap(
                                    partitionGroup ->
                                            partitionGroupConsumerRegions.get(partitionGroup)
                                                    .stream())
                            .distinct()
                            .filter(this::areRegionVerticesAllInCreatedState)
                            .collect(Collectors.toSet());

            maybeScheduleRegions(consumerRegions);
        }
    }

    @Override
    public void onPartitionConsumable(final IntermediateResultPartitionID resultPartitionId) {}

    private void maybeScheduleRegions(final Set<SchedulingPipelinedRegion> regions) {
        final List<SchedulingPipelinedRegion> regionsSorted =
                SchedulingStrategyUtils.sortPipelinedRegionsInTopologicalOrder(
                        schedulingTopology, regions);

        final Map<ConsumedPartitionGroup, Boolean> consumableStatusCache = new HashMap<>();
        for (SchedulingPipelinedRegion region : regionsSorted) {
            // 循环遍历对每个局部进行调度
            maybeScheduleRegion(region, consumableStatusCache);
        }
    }

    private void maybeScheduleRegion(
            final SchedulingPipelinedRegion region,
            final Map<ConsumedPartitionGroup, Boolean> consumableStatusCache) {
        if (!areRegionInputsAllConsumable(region, consumableStatusCache)) {
            return;
        }

        checkState(
                areRegionVerticesAllInCreatedState(region),
                "BUG: trying to schedule a region which is not in CREATED state");

        final List<ExecutionVertexDeploymentOption> vertexDeploymentOptions =
                SchedulingStrategyUtils.createExecutionVertexDeploymentOptions(
                        regionVerticesSorted.get(region), id -> deploymentOption);
        // 调度前准备：根据执行顶点开始计算资源并部署
        schedulerOperations.allocateSlotsAndDeploy(vertexDeploymentOptions);
    }

    private boolean areRegionInputsAllConsumable(
            final SchedulingPipelinedRegion region,
            final Map<ConsumedPartitionGroup, Boolean> consumableStatusCache) {
        for (ConsumedPartitionGroup consumedPartitionGroup :
                region.getAllBlockingConsumedPartitionGroups()) {
            if (crossRegionConsumedPartitionGroups.contains(consumedPartitionGroup)) {
                if (!isCrossRegionConsumedPartitionConsumable(consumedPartitionGroup, region)) {
                    return false;
                }
            } else if (isExternalConsumedPartitionGroup(consumedPartitionGroup, region)) {
                if (!consumableStatusCache.computeIfAbsent(
                        consumedPartitionGroup, this::isConsumedPartitionGroupConsumable)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isConsumedPartitionGroupConsumable(
            final ConsumedPartitionGroup consumedPartitionGroup) {
        for (IntermediateResultPartitionID partitionId : consumedPartitionGroup) {
            if (schedulingTopology.getResultPartition(partitionId).getState()
                    != ResultPartitionState.CONSUMABLE) {
                return false;
            }
        }
        return true;
    }

    private boolean isCrossRegionConsumedPartitionConsumable(
            final ConsumedPartitionGroup consumedPartitionGroup,
            final SchedulingPipelinedRegion pipelinedRegion) {
        for (IntermediateResultPartitionID partitionId : consumedPartitionGroup) {
            if (isExternalConsumedPartition(partitionId, pipelinedRegion)
                    && schedulingTopology.getResultPartition(partitionId).getState()
                            != ResultPartitionState.CONSUMABLE) {
                return false;
            }
        }
        return true;
    }

    private boolean areRegionVerticesAllInCreatedState(final SchedulingPipelinedRegion region) {
        for (SchedulingExecutionVertex vertex : region.getVertices()) {
            if (vertex.getState() != ExecutionState.CREATED) {
                return false;
            }
        }
        return true;
    }

    private boolean isExternalConsumedPartitionGroup(
            ConsumedPartitionGroup consumedPartitionGroup,
            SchedulingPipelinedRegion pipelinedRegion) {

        return isExternalConsumedPartition(consumedPartitionGroup.getFirst(), pipelinedRegion);
    }

    private boolean isExternalConsumedPartition(
            IntermediateResultPartitionID partitionId, SchedulingPipelinedRegion pipelinedRegion) {
        return !pipelinedRegion.contains(
                schedulingTopology.getResultPartition(partitionId).getProducer().getId());
    }

    /** The factory for creating {@link PipelinedRegionSchedulingStrategy}. */
    public static class Factory implements SchedulingStrategyFactory {
        @Override
        public SchedulingStrategy createInstance(
                final SchedulerOperations schedulerOperations,
                final SchedulingTopology schedulingTopology) {
            return new PipelinedRegionSchedulingStrategy(schedulerOperations, schedulingTopology);
        }
    }
}
