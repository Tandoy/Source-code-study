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

package org.apache.hudi.common.util.queue;

import org.apache.hudi.common.util.DefaultSizeEstimator;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.common.util.SizeEstimator;
import org.apache.hudi.exception.HoodieException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Executor which orchestrates concurrent producers and consumers communicating through a bounded in-memory queue. This
 * class takes as input the size limit, queue producer(s), consumer and transformer and exposes API to orchestrate
 * concurrent execution of these actors communicating through a central bounded queue
 */

/**
 *生产者-消费者模型用于解耦生产者与消费者，平衡两者之间的能力不平衡，
 * 该模型广泛应用于各个系统中，Hudi也使用了该模型控制对记录的处理，
 * 即记录会被生产者生产至队列中，然后由消费者从队列中消费，更具体一点，
 * 对于更新操作，生产者会将文件中老的记录放入队列中等待消费者消费，消费后交由HoodieMergeHandle处理；
 * 对于插入操作，生产者会将新记录放入队列中等待消费者消费，消费后交由HandleCreateHandle处理。
 */

/**
 *Hudi采用了生产者-消费者模型来控制记录的处理，
 * 与传统多生产者-多消费者模型不同的是，Hudi现在只支持多生产者-单消费者模型，单消费者意味着Hudi暂时不支持文件的并发写入。
 * 而对于生产消费的队列的实现，Hudi并未仅仅只是基于LinkedBlockingQueue，而是采用了更精细化的速率控制，
 * 保证速率会随着记录负载大小的变化和配置的队列缓存大小而动态变化，这也降低了系统发生OOM的概率。
 */
public class BoundedInMemoryExecutor<I, O, E> {

  private static final Logger LOG = LogManager.getLogger(BoundedInMemoryExecutor.class);

  // Executor service used for launching writer thread.
  private final ExecutorService executorService;
  // Used for buffering records which is controlled by HoodieWriteConfig#WRITE_BUFFER_LIMIT_BYTES.
  private final BoundedInMemoryQueue<I, O> queue;
  // Producers
  private final List<BoundedInMemoryQueueProducer<I>> producers;
  // Consumer
  private final Option<BoundedInMemoryQueueConsumer<O, E>> consumer;

  public BoundedInMemoryExecutor(final long bufferLimitInBytes, BoundedInMemoryQueueProducer<I> producer,
      Option<BoundedInMemoryQueueConsumer<O, E>> consumer, final Function<I, O> transformFunction) {
    this(bufferLimitInBytes, Arrays.asList(producer), consumer, transformFunction, new DefaultSizeEstimator<>());
  }

  public BoundedInMemoryExecutor(final long bufferLimitInBytes, List<BoundedInMemoryQueueProducer<I>> producers,
      Option<BoundedInMemoryQueueConsumer<O, E>> consumer, final Function<I, O> transformFunction,
      final SizeEstimator<O> sizeEstimator) {
    this.producers = producers;
    this.consumer = consumer;
    // Ensure single thread for each producer thread and one for consumer
    this.executorService = Executors.newFixedThreadPool(producers.size() + 1);
    this.queue = new BoundedInMemoryQueue<>(bufferLimitInBytes, transformFunction, sizeEstimator);
  }

  /**
   * Callback to implement environment specific behavior before executors (producers/consumer) run.
   */
  public void preExecute() {
    // Do Nothing in general context
  }

  /**
   * Start all Producers.
   */
  public ExecutorCompletionService<Boolean> startProducers() {
    // Latch to control when and which producer thread will close the queue
    // 协调生产者线程与消费者线程的退出动作
    final CountDownLatch latch = new CountDownLatch(producers.size());
    final ExecutorCompletionService<Boolean> completionService =
        new ExecutorCompletionService<Boolean>(executorService);
    // 每个生产者
    producers.stream().map(producer -> {
      return completionService.submit(() -> {
        try {
          // 生产前相关处理
          preExecute();
          // 插入更新
          producer.produce(queue);
        } catch (Exception e) {
          LOG.error("error producing records", e);
          queue.markAsFailed(e);
          throw e;
        } finally {
          synchronized (latch) {
            latch.countDown();
            if (latch.getCount() == 0) {
              // Mark production as done so that consumer will be able to exit
              queue.close();
            }
          }
        }
        return true;
      });
    }).collect(Collectors.toList());
    return completionService;
  }

  /**
   * Start only consumer.
   */
  private Future<E> startConsumer() {
    return consumer.map(consumer -> {
      return executorService.submit(() -> {
        LOG.info("starting consumer thread");
        preExecute();
        try {
          E result = consumer.consume(queue);
          LOG.info("Queue Consumption is done; notifying producer threads");
          return result;
        } catch (Exception e) {
          LOG.error("error consuming records", e);
          queue.markAsFailed(e);
          throw e;
        }
      });
    }).orElse(CompletableFuture.completedFuture(null));
  }

  /**
   * Main API to run both production and consumption.
   * 开始记录的处理
   * 启动所有生产者和单个消费者进行处理。
   */
  public E execute() {
    try {
      // 启动所有的生产者
      ExecutorCompletionService<Boolean> producerService = startProducers();
      // 启动单个消费者
      Future<E> future = startConsumer();
      // Wait for consumer to be done
      return future.get();
    } catch (Exception e) {
      throw new HoodieException(e);
    }
  }

  public boolean isRemaining() {
    return queue.iterator().hasNext();
  }

  public void shutdownNow() {
    executorService.shutdownNow();
  }

  public BoundedInMemoryQueue<I, O> getQueue() {
    return queue;
  }
}
