/**
 * 第六步：每条信息绑定回调函数
 * 由于采用异步发送的方式，每条信息发送至broker后会调用回调函数进行相关状态的判断处理
 */
private static class InterceptorCallback<K, V> implements Callback {
    private final Callback userCallback;
    private final ProducerInterceptors<K, V> interceptors;
    private final TopicPartition tp;

    public InterceptorCallback(Callback userCallback, ProducerInterceptors<K, V> interceptors,
                               TopicPartition tp) {
        this.userCallback = userCallback;
        this.interceptors = interceptors;
        this.tp = tp;
    }

    public void onCompletion(RecordMetadata metadata, Exception exception) {
        //判断是否存在拦截器
        if (this.interceptors != null) {
            if (metadata == null) {
                //元数据为空则创建并返回异常信息
                this.interceptors.onAcknowledgement(new RecordMetadata(tp, -1, -1, Record.NO_TIMESTAMP, -1, -1, -1),
                        exception);
            } else {
                this.interceptors.onAcknowledgement(metadata, exception);
            }
        }
        if (this.userCallback != null)
            //拦截器为空且回调函数存在则返回元数据信息以及异常信息
            this.userCallback.onCompletion(metadata, exception);
    }
}
