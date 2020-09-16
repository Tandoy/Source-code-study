/**
 * 第五步：根据元数据信息，封装分区对象
 */
package org.apache.kafka.common;

import java.io.Serializable;
//调用TopicPartition构造方法传入信息所属主题以及分区号，进行分区对象的创建
public final class TopicPartition implements Serializable {

    private int hash = 0;
    private final int partition;
    private final String topic;

    public TopicPartition(String topic, int partition) {
        this.partition = partition;
        this.topic = topic;
    }

    public int partition() {
        return partition;
    }

    public String topic() {
        return topic;
    }

    @Override
    public int hashCode() {
        if (hash != 0)
            return hash;
        final int prime = 31;
        int result = 1;
        result = prime * result + partition;
        result = prime * result + ((topic == null) ? 0 : topic.hashCode());
        this.hash = result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TopicPartition other = (TopicPartition) obj;
        if (partition != other.partition)
            return false;
        if (topic == null) {
            if (other.topic != null)
                return false;
        } else if (!topic.equals(other.topic))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return topic + "-" + partition;
    }

}
