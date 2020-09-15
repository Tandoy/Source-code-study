/**
 * Implementation of asynchronously send a record to a topic.
 */
/**
 * 经过produce初始化就开始异步发送消息至broker
 * 以下代码为KafkaProducer类中发生信息的主要方法，通过此方法可了解 producer发送消息的核心流程
 */
private Future<RecordMetadata> doSend(ProducerRecord<K, V> record, Callback callback) {
        TopicPartition tp = null;
        try {
        /**
         * 第一步：元数据的更新
         * maxBlockTimeMs：最长等待时间
         * clusterAndWaitTime.waitedOnMetadataMs：拉取元数据消耗的时间
         * remainingWaitMs = 最长等待时间 - 拉取元数据消耗的时间 => 还能等待多久
         */
        ClusterAndWaitTime clusterAndWaitTime = waitOnMetadata(record.topic(), record.partition(), maxBlockTimeMs);
        long remainingWaitMs = Math.max(0, maxBlockTimeMs - clusterAndWaitTime.waitedOnMetadataMs);
        //进行元数据的更新
        Cluster cluster = clusterAndWaitTime.cluster;
        /**
         * 第二步：序列化key-value
         */
        byte[] serializedKey;
        try {
        serializedKey = keySerializer.serialize(record.topic(), record.key());
        } catch (ClassCastException cce) {
        throw new SerializationException("Can't convert key of class " + record.key().getClass().getName() +
        " to class " + producerConfig.getClass(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG).getName() +
        " specified in key.serializer");
        }
        byte[] serializedValue;
        try {
        serializedValue = valueSerializer.serialize(record.topic(), record.value());
        } catch (ClassCastException cce) {
        throw new SerializationException("Can't convert value of class " + record.value().getClass().getName() +
        " to class " + producerConfig.getClass(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG).getName() +
        " specified in value.serializer");
        }
        /**
         * 第三步：根据分区器选择此消息应发送的分区
         */
        int partition = partition(record, serializedKey, serializedValue, cluster);
        int serializedSize = Records.LOG_OVERHEAD + Record.recordSize(serializedKey, serializedValue);
        /**
         * 第四步：校验序列化后的信息是否超出最大值（默认1M，实际生产中大多设置为10M）
         */
        ensureValidRecordSize(serializedSize);

        /**
         * 第五步：根据元数据信息，封装分区对象
         */
        tp = new TopicPartition(record.topic(), partition);
        long timestamp = record.timestamp() == null ? time.milliseconds() : record.timestamp();
        log.trace("Sending record {} with callback {} to topic {} partition {}", record, callback, record.topic(), partition);
        // producer callback will make sure to call both 'callback' and interceptor callback
        /**
         * 第六步：每条信息绑定回调函数
         */
        Callback interceptCallback = this.interceptors == null ? callback : new InterceptorCallback<>(callback, this.interceptors, tp);
        /**
         * 第七步：消息放入缓冲区(32M内存),由此对象将消息封装成一个批次进行发送
         */
        RecordAccumulator.RecordAppendResult result = accumulator.append(tp, timestamp, serializedKey, serializedValue, interceptCallback, remainingWaitMs);
        if (result.batchIsFull || result.newBatchCreated) {
        log.trace("Waking up the sender since topic {} partition {} is either full or getting a new batch", record.topic(), partition);
        /**
         * 第八步：唤醒sender发送数据线程
         * 唤醒条件：1.此批次内存已满
         *         2.新批次已被创建
         */
        this.sender.wakeup();
        }
        return result.future;
        // handling exceptions and record the errors;
        // for API exceptions return them in the future,
        // for other exceptions throw directly
        } catch (ApiException e) {
        log.debug("Exception occurred during message send:", e);
        if (callback != null)
        callback.onCompletion(null, e);
        this.errors.record();
        if (this.interceptors != null)
        this.interceptors.onSendError(record, tp, e);
        return new FutureFailure(e);
        } catch (InterruptedException e) {
        this.errors.record();
        if (this.interceptors != null)
        this.interceptors.onSendError(record, tp, e);
        throw new InterruptException(e);
        } catch (BufferExhaustedException e) {
        this.errors.record();
        this.metrics.sensor("buffer-exhausted-records").record();
        if (this.interceptors != null)
        this.interceptors.onSendError(record, tp, e);
        throw e;
        } catch (KafkaException e) {
        this.errors.record();
        if (this.interceptors != null)
        this.interceptors.onSendError(record, tp, e);
        throw e;
        } catch (Exception e) {
        // we notify interceptor about all exceptions, since onSend is called before anything else in this method
        if (this.interceptors != null)
        this.interceptors.onSendError(record, tp, e);
        throw e;
        }
        }

