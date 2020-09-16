/**
 * 第四步：对序列化后的key-value进行大小校验
 */
private void ensureValidRecordSize(int size) {
        //判断此条信息是否超出一条信息设置的大小 默认1M（实际生产中多为10M）
        if (size > this.maxRequestSize)
        throw new RecordTooLargeException("The message is " + size +
        " bytes when serialized which is larger than the maximum request size you have configured with the " +
        ProducerConfig.MAX_REQUEST_SIZE_CONFIG +
        " configuration.");

        //判断此条信息是否超出整个RecordAccumulator内存大小 默认32M
        if (size > this.totalMemorySize)
        throw new RecordTooLargeException("The message is " + size +
        " bytes when serialized which is larger than the total memory buffer you have configured with the " +
        ProducerConfig.BUFFER_MEMORY_CONFIG +
        " configuration.");

        /**
         * 合理设置maxRequestSize、totalMemorySize可提高消息网络传输效率
         */
        }