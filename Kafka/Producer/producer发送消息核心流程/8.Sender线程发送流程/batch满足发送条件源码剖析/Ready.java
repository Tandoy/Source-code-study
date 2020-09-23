/**
 * 在RecordAccumulator类中ready方法是发送一个批次消息主方法
 */
public ReadyCheckResult ready(Cluster cluster, long nowMs) {
        Set<Node> readyNodes = new HashSet<>();
        long nextReadyCheckDelayMs = Long.MAX_VALUE;
        Set<String> unknownLeaderTopics = new HashSet<>();
        // 若exhausted = true 即内存池中内存不够
        boolean exhausted = this.free.queued() > 0;
        //遍历所有分区
        for (Map.Entry<TopicPartition, Deque<RecordBatch>> entry : this.batches.entrySet()) {
            //获取分区
        TopicPartition part = entry.getKey();
        //获取分区对应的队列
        Deque<RecordBatch> deque = entry.getValue();
        //获取对应分区的leader partition的主机
        Node leader = cluster.leaderFor(part);
synchronized (deque) {
        //标识找不到主机的分区
        if (leader == null && !deque.isEmpty()) {
        // This is a partition for which leader is not known, but messages are available to send.
        // Note that entries are currently not removed from batches when deque is empty.
        unknownLeaderTopics.add(part.topic());
        } else if (!readyNodes.contains(leader) && !muted.contains(part)) {
            //从队列中拿取队首批次
        RecordBatch batch = deque.peekFirst();
        if (batch != null) {
        /**
         * batch.attempts：重试次数
         * batch.lastAttemptMs：上一次重试的时间
         * retryBackoffMs：重试的时间间隔
         * backingOff：重新发送数据的时间
         *
         * waitedTimeMs：已等待时间
         *
         * timeToWaitMs：不管此时此批次是否满足发送大小都直接发送的等待时间
         *
         * timeLeftMs：还需等待时长
         *
         * deque.size() > 1：说明队首批次已写满 full：是否存在写满的批次
         *
         * expired =true 发送消息时间已到
         */
        boolean backingOff = batch.attempts > 0 && batch.lastAttemptMs + retryBackoffMs > nowMs;
        long waitedTimeMs = nowMs - batch.lastAttemptMs;
        long timeToWaitMs = backingOff ? : lingerMs;
        long timeLeftMs = Math.max(timeToWaitMs - waitedTimeMs, 0);
        boolean full = deque.size() > 1 || batch.records.isFull();
        boolean expired = waitedTimeMs >= timeToWaitMs;
        /**
         * sendable：是否发送判断条件
         *   1.full 若一个批次已写满直接发送，不管发送等待时长是否满足
         *   2.expired 发送等待时长已满足，不管批次有没有写满
         *   3.exhausted 当内存池不够时也选择发送此批次消息（一批次消息发送后会释放内存）
         */
        boolean sendable = full || expired || exhausted || closed || flushInProgress();
        if (sendable && !backingOff) { //达到发送消息条件
            //对应分区的leader partition的主机
        readyNodes.add(leader);
        } else {
        // Note that this results in a conservative estimate since an un-sendable partition may have
        // a leader that will later be found to have sendable data. However, this is good enough
        // since we'll just wake up and then sleep again for the remaining time.
        nextReadyCheckDelayMs = Math.min(timeLeftMs, nextReadyCheckDelayMs);
        }
        }
        }
        }
        }

        return new ReadyCheckResult(readyNodes, nextReadyCheckDelayMs, unknownLeaderTopics);
        }