/**
 * 第一步：元数据的更新
 */
private ClusterAndWaitTime waitOnMetadata(String topic, Integer partition, long maxWaitMs) throws InterruptedException {
        // add topic to metadata topic list if it is not there already and reset expiry
        metadata.add(topic);
        //尝试获取元数据信息
        Cluster cluster = metadata.fetch();
        //获取主题对应的分区
        Integer partitionsCount = cluster.partitionCountForTopic(topic);
        // Return cached metadata if we have it, and if the record's partition is either undefined
        // or within the known partition range
        //实际当初始化后，此条消息在broker中没有，即broker端没有
        if (partitionsCount != null && (partition == null || partition < partitionsCount))
        //直接new一个新cluster
        return new ClusterAndWaitTime(cluster, 0);
        //开始时间
        long begin = time.milliseconds();
        //可用等待时长，刚开始等于最大可等待时间默认1min
        long remainingWaitMs = maxWaitMs;
        //已消耗时间
        long elapsed;
        // Issue metadata requests until we have metadata for the topic or maxWaitTimeMs is exceeded.
        // In case we already have cached metadata for the topic, but the requested partition is greater
        // than expected, issue an update request only once. This is necessary in case the metadata
        // is stale and the number of partitions for this topic has increased in the meantime.
        do {
        log.trace("Requesting metadata update for topic {}.", topic);
        int version = metadata.requestUpdate();
        /**
         * 唤醒发送消息线程
         */
        sender.wakeup();
        try {
        //更新元数据版本号以及可等待时长
        metadata.awaitUpdate(version, remainingWaitMs);
        } catch (TimeoutException ex) {
        // Rethrow with original maxWaitMs to prevent logging exception with remainingWaitMs
        throw new TimeoutException("Failed to update metadata after " + maxWaitMs + " ms.");
        }
        //尝试拉取元数据信息
        cluster = metadata.fetch();
        //计算已消耗时间
        elapsed = time.milliseconds() - begin;
        //超时处理
        if (elapsed >= maxWaitMs)
        throw new TimeoutException("Failed to update metadata after " + maxWaitMs + " ms.");
        //是否授权处理
        if (cluster.unauthorizedTopics().contains(topic))
        throw new TopicAuthorizationException(topic);
        //计算当前可等待时长
        remainingWaitMs = maxWaitMs - elapsed;
        //根据从broker获取的更新此主题的分区信息
        partitionsCount = cluster.partitionCountForTopic(topic);
        } while (partitionsCount == null);

        if (partition != null && partition >= partitionsCount) {
        throw new KafkaException(
        String.format("Invalid partition given with record: %d is not in the range [0...%d).", partition, partitionsCount));
        }
        //返回 元数据信息实例+已消耗时间
        return new ClusterAndWaitTime(cluster, elapsed);
        }