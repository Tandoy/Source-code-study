/**
 * Updates the cluster metadata. If topic expiry is enabled, expiry time
 * is set for topics if required and expired topics are removed from the metadata.
 */
/**
 * 在produce初始化过程中，metadata调用update方法进行元数据相关操作
 */
public final class Metadata {
    private boolean needMetadataForAllTopics;
    private final boolean topicExpiryEnabled;

    public synchronized void update(Cluster cluster, long now) {
        Objects.requireNonNull(cluster, "cluster should not be null");

        this.needUpdate = false;
        this.lastRefreshMs = now;
        this.lastSuccessfulRefreshMs = now;
        this.version += 1;

        if (topicExpiryEnabled) { //默认值为false
            // Handle expiry of topics from the metadata refresh set.
            for (Iterator<Map.Entry<String, Long>> it = topics.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Long> entry = it.next();
                long expireMs = entry.getValue();
                if (expireMs == TOPIC_EXPIRY_NEEDS_UPDATE)
                    entry.setValue(now + TOPIC_EXPIRY_MS);
                else if (expireMs <= now) {
                    it.remove();
                    log.debug("Removing unused topic {} from the metadata list, expiryMs {} now {}", entry.getKey(), expireMs, now);
                }
            }
        }

        for (Listener listener : listeners)
            listener.onMetadataUpdate(cluster);

        String previousClusterId = cluster.clusterResource().clusterId();

        if (this.needMetadataForAllTopics) { //默认值同样false
            // the listener may change the interested topics, which could cause another metadata refresh.
            // If we have already fetched all topics, however, another fetch should be unnecessary.
            this.needUpdate = false;
            this.cluster = getClusterForCurrentTopics(cluster);
        } else {
            //cluster代表kafka集群的元数据信息
            //即在produce初始化阶段，produce并没有从集群中拉取元数据信息，而是根据用户设置进行赋值操作
            this.cluster = cluster;
        }

        // The bootstrap cluster is guaranteed not to have any useful information
        if (!cluster.isBootstrapConfigured()) {
            String clusterId = cluster.clusterResource().clusterId();
            if (clusterId == null ? previousClusterId != null : !clusterId.equals(previousClusterId))
                log.info("Cluster ID: {}", cluster.clusterResource().clusterId());
            clusterResourceListeners.onUpdate(cluster.clusterResource());
        }

        notifyAll();
        log.debug("Updated cluster metadata version {} to {}", this.version, this.cluster);
    }
}