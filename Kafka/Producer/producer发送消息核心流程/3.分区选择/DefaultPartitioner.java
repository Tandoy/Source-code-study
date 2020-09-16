/**
 * 第三步：分区选择
 * Kafka DefaultPartitioner 实现 Partitioner接口
 */
public class DefaultPartitioner implements Partitioner {
    //原子类，累加器
    private final AtomicInteger counter = new AtomicInteger(new Random().nextInt());

    public void configure(Map<String, ?> configs) {}

    /**
     * 分区主方法
     */
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //根据已经获取到的元数据得到此条信息所属主题的分区信息
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        //分区总和
        int numPartitions = partitions.size();
        //分区策略一：此条消息没有设置key
        if (keyBytes == null) {
            //自增累加器 +1
            int nextValue = counter.getAndIncrement();
            //获取信息所属主题的当前可用分区数
            List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
            if (availablePartitions.size() > 0) {
                /**
                 * 若当前可用分区数>0
                 * 分区算法：使用累加器对可用分区进行取模
                 * 如果当前可用分区数为10 则只会有0~9号分区，以达到负载均衡的效果
                 */
                int part = Utils.toPositive(nextValue) % availablePartitions.size();
                return availablePartitions.get(part).partition();
            } else {
                //若当前没有可用分区，则给一个non-available partition
                return Utils.toPositive(nextValue) % numPartitions;
            }
        } else {
            // hash the keyBytes to choose a partition
            //分区策略二：信息有key，对key进行hash再根据分区总数取模
            //这样保证相同key的消息则会在同一分区中
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
        }
    }

    public void close() {}

}
