/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.apache.kafka.clients;

import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.network.NetworkReceive;
import org.apache.kafka.common.network.Selectable;
import org.apache.kafka.common.network.Send;
import org.apache.kafka.common.protocol.ApiKeys;
import org.apache.kafka.common.protocol.Errors;
import org.apache.kafka.common.protocol.ProtoUtils;
import org.apache.kafka.common.protocol.types.Struct;
import org.apache.kafka.common.requests.MetadataRequest;
import org.apache.kafka.common.requests.MetadataResponse;
import org.apache.kafka.common.requests.RequestHeader;
import org.apache.kafka.common.requests.RequestSend;
import org.apache.kafka.common.requests.ResponseHeader;
import org.apache.kafka.common.utils.Time;
import org.apache.kafka.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A network client for asynchronous request/response network i/o. This is an internal class used to implement the
 * user-facing producer and consumer clients.
 * <p>
 * This class is not thread-safe!
 */
public class NetworkClient implements KafkaClient {

    private static final Logger log = LoggerFactory.getLogger(NetworkClient.class);

    /* the selector used to perform network i/o */
    private final Selectable selector;

    private final MetadataUpdater metadataUpdater;

    private final Random randOffset;

    /* the state of each node's connection */
    private final ClusterConnectionStates connectionStates;

    /* the set of requests currently being sent or awaiting a response */
    private final InFlightRequests inFlightRequests;

    /* the socket send buffer size in bytes */
    private final int socketSendBuffer;

    /* the socket receive size buffer in bytes */
    private final int socketReceiveBuffer;

    /* the client id used to identify this client in requests to the server */
    private final String clientId;

    /* the current correlation id to use when sending requests to servers */
    private int correlation;

    /* max time in ms for the producer to wait for acknowledgement from server*/
    private final int requestTimeoutMs;

    private final Time time;

    public NetworkClient(Selectable selector,
                         Metadata metadata,
                         String clientId,
                         int maxInFlightRequestsPerConnection,
                         long reconnectBackoffMs,
                         int socketSendBuffer,
                         int socketReceiveBuffer,
                         int requestTimeoutMs,
                         Time time) {
        this(null, metadata, selector, clientId, maxInFlightRequestsPerConnection,
                reconnectBackoffMs, socketSendBuffer, socketReceiveBuffer, requestTimeoutMs, time);
    }

    public NetworkClient(Selectable selector,
                         MetadataUpdater metadataUpdater,
                         String clientId,
                         int maxInFlightRequestsPerConnection,
                         long reconnectBackoffMs,
                         int socketSendBuffer,
                         int socketReceiveBuffer,
                         int requestTimeoutMs,
                         Time time) {
        this(metadataUpdater, null, selector, clientId, maxInFlightRequestsPerConnection, reconnectBackoffMs,
                socketSendBuffer, socketReceiveBuffer, requestTimeoutMs, time);
    }

    private NetworkClient(MetadataUpdater metadataUpdater,
                          Metadata metadata,
                          Selectable selector,
                          String clientId,
                          int maxInFlightRequestsPerConnection,
                          long reconnectBackoffMs,
                          int socketSendBuffer,
                          int socketReceiveBuffer,
                          int requestTimeoutMs,
                          Time time) {

        /* It would be better if we could pass `DefaultMetadataUpdater` from the public constructor, but it's not
         * possible because `DefaultMetadataUpdater` is an inner class and it can only be instantiated after the
         * super constructor is invoked.
         */
        if (metadataUpdater == null) {
            if (metadata == null)
                throw new IllegalArgumentException("`metadata` must not be null");
            this.metadataUpdater = new DefaultMetadataUpdater(metadata);
        } else {
            this.metadataUpdater = metadataUpdater;
        }
        this.selector = selector;
        this.clientId = clientId;
        this.inFlightRequests = new InFlightRequests(maxInFlightRequestsPerConnection);
        this.connectionStates = new ClusterConnectionStates(reconnectBackoffMs);
        this.socketSendBuffer = socketSendBuffer;
        this.socketReceiveBuffer = socketReceiveBuffer;
        this.correlation = 0;
        this.randOffset = new Random();
        this.requestTimeoutMs = requestTimeoutMs;
        this.time = time;
    }

    /**
     * Begin connecting to the given node, return true if we are already connected and ready to send to that node.
     *
     * @param node The node to check
     * @param now The current timestamp
     * @return True if we are ready to send to the given node
     */
    @Override
    public boolean ready(Node node, long now) {
        //如果当前检查的节点为null，就报异常。
        if (node.isEmpty())
            throw new IllegalArgumentException("Cannot connect to empty node " + node);
        //判断要发送消息的主机，是否具备发送消息的条件
        if (isReady(node, now))
            return true;
        //判断是否可以尝试去建立网络
        if (connectionStates.canConnect(node.idString(), now))
            // if we are interested in sending to a node and we don't have a connection to it, initiate one
            //初始化连接
            initiateConnect(node, now);

        return false;
    }

    /**
     * Closes the connection to a particular node (if there is one).
     *
     * @param nodeId The id of the node
     */
    @Override
    public void close(String nodeId) {
        selector.close(nodeId);
        for (ClientRequest request : inFlightRequests.clearAll(nodeId))
            metadataUpdater.maybeHandleDisconnection(request);
        connectionStates.remove(nodeId);
    }

    /**
     * Returns the number of milliseconds to wait, based on the connection state, before attempting to send data. When
     * disconnected, this respects the reconnect backoff time. When connecting or connected, this handles slow/stalled
     * connections.
     *
     * @param node The node to check
     * @param now The current timestamp
     * @return The number of milliseconds to wait.
     */
    @Override
    public long connectionDelay(Node node, long now) {
        return connectionStates.connectionDelay(node.idString(), now);
    }

    /**
     * Check if the connection of the node has failed, based on the connection state. Such connection failure are
     * usually transient and can be resumed in the next {@link #ready(org.apache.kafka.common.Node, long)} }
     * call, but there are cases where transient failures needs to be caught and re-acted upon.
     *
     * @param node the node to check
     * @return true iff the connection has failed and the node is disconnected
     */
    @Override
    public boolean connectionFailed(Node node) {
        return connectionStates.connectionState(node.idString()).equals(ConnectionState.DISCONNECTED);
    }

    /**
     * Check if the node with the given id is ready to send more requests.
     *
     * @param node The node
     * @param now The current time in ms
     * @return true if the node is ready
     */
    @Override
    public boolean isReady(Node node, long now) {
        // if we need to update our metadata now declare all requests unready to make metadata requests first
        // priority
        // !metadataUpdater.isUpdateDue(now)
        //我们要发送写数据请求的时候，不能是正在更新元数据的时候。
        return !metadataUpdater.isUpdateDue(now) && canSendRequest(node.idString());
    }

    /**
     * Are we connected and ready and able to send more requests to the given connection?
     *
     * @param node The node
     */
    private boolean canSendRequest(String node) {
        /**
         * connectionStates.isConnected(node):
         *  生产者：多个连接，缓存多个连接（跟我们的broker的节点数是一样的）
         *     判断缓存里面是否已经把这个连接给建立好了。
         * selector.isChannelReady(node)：
         *      java NIO：selector
         *      selector -> 绑定了多个KafkaChannel(java socketChannel)
         *      一个kafkaChannel就代表一个连接。
         *
         *  nFlightRequests.canSendMore(node)：
         *  每个往broker主机上面发送消息的连接，最多能容忍5个消息，发送出去了
         *  但是还没有接受到响应。
         *  发送数据的顺序。
         *  1,2,3,4,5
         *
         *  2,3,4，5，1
         */
        return connectionStates.isConnected(node)
                && selector.isChannelReady(node)
                && inFlightRequests.canSendMore(node);
    }


    /**
     * Queue up the given request for sending. Requests can only be sent out to ready nodes.
     *
     * @param request The request
     * @param now The current timestamp
     */
    @Override
    public void send(ClientRequest request, long now) {
        String nodeId = request.request().destination();
        if (!canSendRequest(nodeId))
            throw new IllegalStateException("Attempt to send a request to node " + nodeId + " which is not ready.");
        //TODO 看上去就是关键的代码。
        doSend(request, now);
    }

    private void doSend(ClientRequest request, long now) {
        request.setSendTimeMs(now);
        //这儿往inFlightRequests 组件里存 Request请求。
        //存储的就是还没有收到响应的请求。
        //这个里面默认最多能存5个请求。
        //其实我们可以猜想一个事，如果我们的请求发送出去了
        //然后也成功的接受到了响应，后面就会到这儿把这个请求移除。
        this.inFlightRequests.add(request);
        //TODO
        selector.send(request.request());
    }

    /**
     * Do actual reads and writes to sockets.
     *
     * @param timeout The maximum amount of time to wait (in ms) for responses if there are none immediately,
     *                must be non-negative. The actual timeout will be the minimum of timeout, request timeout and
     *                metadata timeout
     * @param now The current time in milliseconds
     * @return The list of responses received
     */
    @Override
    public List<ClientResponse> poll(long timeout, long now) {
        /**
         * 在这个方法里面有涉及到kafka的网络的方法，但是
         * 目前我们还没有给大家讲kafka的网络，所以我们分析的时候
         * 暂时不用分析得特别的详细，我们大概知道是如何获取到元数据
         * 即可。等我们分析完了kafka的网络以后，我们在回头看这儿的代码
         * 的时候，其实代码就比较简单了。
         */
        //步骤一：封装了一个要拉取元数据请求
        long metadataTimeout = metadataUpdater.maybeUpdate(now);
        try {


            //步骤二： 发送请求，进行复杂的网络操作
            //但是我们目前还没有学习到kafka的网络
            //所以这儿大家就只需要知道这儿会发送网络请求。
            //TODO 执行网络IO的操作。
            this.selector.poll(Utils.min(timeout, metadataTimeout, requestTimeoutMs));
        } catch (IOException e) {
            log.error("Unexpected error during I/O", e);
        }

        // process completed actions
        long updatedNow = this.time.milliseconds();
        List<ClientResponse> responses = new ArrayList<>();
        handleCompletedSends(responses, updatedNow);
        //步骤三：处理响应，响应里面就会有我们需要的元数据。
        /**
         * 这个地方是我们在看生产者是如何获取元数据的时候，看的。
         * 其实Kafak获取元数据的流程跟我们发送消息的流程是一模一样。
         * 获取元数据 -》 判断网络连接是否建立好 -》 建立网络连接
         * -》 发送请求（获取元数据的请求） -》 服务端发送回来响应（带了集群的元数据信息）
         *
         */
        handleCompletedReceives(responses, updatedNow);
        handleDisconnections(responses, updatedNow);
        handleConnections();
        //处理超时的请求
        handleTimedOutRequests(responses, updatedNow);

        // invoke callbacks
        for (ClientResponse response : responses) {
            if (response.request().hasCallback()) {
                try {
                    //调用的响应的里面的我们之前发送出去的请求的回调函数
                    //看到了这儿，我们回头再去看一下
                    //我们当时发送请求的时候，是如何封装这个请求。
                    //不过虽然目前我们还没看到，但是我们可以大胆猜一下。
                    //当时封装网络请求的时候，肯定是给他绑定了一个回调函数。
                    response.request().callback().onComplete(response);
                } catch (Exception e) {
                    log.error("Uncaught error in request completion:", e);
                }
            }
        }

        return responses;
    }

    /**
     * Get the number of in-flight requests
     */
    @Override
    public int inFlightRequestCount() {
        return this.inFlightRequests.inFlightRequestCount();
    }

    /**
     * Get the number of in-flight requests for a given node
     */
    @Override
    public int inFlightRequestCount(String node) {
        return this.inFlightRequests.inFlightRequestCount(node);
    }

    /**
     * Generate a request header for the given API key
     *
     * @param key The api key
     * @return A request header with the appropriate client id and correlation id
     */
    @Override
    public RequestHeader nextRequestHeader(ApiKeys key) {
        return new RequestHeader(key.id, clientId, correlation++);
    }

    /**
     * Generate a request header for the given API key and version
     *
     * @param key The api key
     * @param version The api version
     * @return A request header with the appropriate client id and correlation id
     */
    @Override
    public RequestHeader nextRequestHeader(ApiKeys key, short version) {
        return new RequestHeader(key.id, version, clientId, correlation++);
    }

    /**
     * Interrupt the client if it is blocked waiting on I/O.
     */
    @Override
    public void wakeup() {
        this.selector.wakeup();
    }

    /**
     * Close the network client
     */
    @Override
    public void close() {
        this.selector.close();
    }

    /**
     * Choose the node with the fewest outstanding requests which is at least eligible for connection. This method will
     * prefer a node with an existing connection, but will potentially choose a node for which we don't yet have a
     * connection if all existing connections are in use. This method will never choose a node for which there is no
     * existing connection and from which we have disconnected within the reconnect backoff period.
     *
     * @return The node with the fewest in-flight requests.
     */
    @Override
    public Node leastLoadedNode(long now) {
        List<Node> nodes = this.metadataUpdater.fetchNodes();
        int inflight = Integer.MAX_VALUE;
        Node found = null;

        int offset = this.randOffset.nextInt(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            int idx = (offset + i) % nodes.size();
            Node node = nodes.get(idx);
            int currInflight = this.inFlightRequests.inFlightRequestCount(node.idString());
            if (currInflight == 0 && this.connectionStates.isConnected(node.idString())) {
                // if we find an established connection with no in-flight requests we can stop right away
                return node;
            } else if (!this.connectionStates.isBlackedOut(node.idString(), now) && currInflight < inflight) {
                // otherwise if this is the best we have found so far, record that
                inflight = currInflight;
                found = node;
            }
        }

        return found;
    }

    public static Struct parseResponse(ByteBuffer responseBuffer, RequestHeader requestHeader) {
        ResponseHeader responseHeader = ResponseHeader.parse(responseBuffer);
        // Always expect the response version id to be the same as the request version id
        short apiKey = requestHeader.apiKey();
        short apiVer = requestHeader.apiVersion();
        Struct responseBody = ProtoUtils.responseSchema(apiKey, apiVer).read(responseBuffer);
        correlate(requestHeader, responseHeader);
        return responseBody;
    }

    /**
     * Post process disconnection of a node
     *
     * @param responses The list of responses to update
     * @param nodeId Id of the node to be disconnected
     * @param now The current time
     */
    private void processDisconnection(List<ClientResponse> responses, String nodeId, long now) {
        //修改连接状态
        connectionStates.disconnected(nodeId, now);
        //
        for (ClientRequest request : this.inFlightRequests.clearAll(nodeId)) {
            log.trace("Cancelled request {} due to node {} being disconnected", request, nodeId);
            if (!metadataUpdater.maybeHandleDisconnection(request))
                //对这些请求进行处理
                //大家会看到一个比较有意思的事
                //自己封装了一个响应。这个响应里面没有服务端响应消息（服务端没给响应）
                //失去连接的状态表标识为true
                responses.add(new ClientResponse(request, now, true, null));
        }
    }

    /**
     * Iterate over all the inflight requests and expire any requests that have exceeded the configured requestTimeout.
     * The connection to the node associated with the request will be terminated and will be treated as a disconnection.
     *
     * @param responses The list of responses to update
     * @param now The current time
     */
    private void handleTimedOutRequests(List<ClientResponse> responses, long now) {
        //获取到请求超时的主机。
        List<String> nodeIds = this.inFlightRequests.getNodesWithTimedOutRequests(now, this.requestTimeoutMs);
        for (String nodeId : nodeIds) {
            // close connection to the node
            //关闭请求超时的主机的连接
            this.selector.close(nodeId);
            log.debug("Disconnecting from node {} due to request timeout.", nodeId);
            //我们猜应该是会去修改 连接的状态
            processDisconnection(responses, nodeId, now);
        }

        // we disconnected, so we should probably refresh our metadata
        if (nodeIds.size() > 0)
            metadataUpdater.requestUpdate();
    }

    /**
     * Handle any completed request send. In particular if no response is expected consider the request complete.
     *
     * @param responses The list of responses to update
     * @param now The current time
     */
    private void handleCompletedSends(List<ClientResponse> responses, long now) {
        // if no response is expected then when the send is completed, return it
        for (Send send : this.selector.completedSends()) {
            ClientRequest request = this.inFlightRequests.lastSent(send.destination());
            if (!request.expectResponse()) {
                this.inFlightRequests.completeLastSent(send.destination());
                responses.add(new ClientResponse(request, now, false, null));
            }
        }
    }

    /**
     * Handle any completed receives and update the response list with the responses received.
     *
     * @param responses The list of responses to update
     * @param now The current time
     */
    private void handleCompletedReceives(List<ClientResponse> responses, long now) {
        for (NetworkReceive receive : this.selector.completedReceives()) {
            //获取broker id
            String source = receive.source();
            /**
             * kafka 有这样的一个机制：每个连接可以容忍5个发送出去了，但是还没接收到响应的请求。
             */
            //从数据结构里面移除已经接收到响应的请求。
            //把之前存入进去的请求也获取到了
            ClientRequest req = inFlightRequests.completeNext(source);
            //解析服务端发送回来的请求（里面有响应的结果数据）
            Struct body = parseResponse(receive.payload(), req.request().header());
            //TODO 如果是关于元数据信息的响应
            if (!metadataUpdater.maybeHandleCompletedReceive(req, now, body))
                //解析完了以后就把封装成一个一个的cilentResponse
                //body 存储的是响应的内容
                //req 发送出去的那个请求信息
                responses.add(new ClientResponse(req, now, false, body));
        }
    }

    /**
     * Handle any disconnected connections
     *
     * @param responses The list of responses that completed with the disconnection
     * @param now The current time
     */
    private void handleDisconnections(List<ClientResponse> responses, long now) {
        for (String node : this.selector.disconnected()) {
            log.debug("Node {} disconnected.", node);
            processDisconnection(responses, node, now);
        }
        // we got a disconnect so we should probably refresh our metadata and see if that broker is dead
        if (this.selector.disconnected().size() > 0)
            metadataUpdater.requestUpdate();
    }

    /**
     * Record any newly completed connections
     */
    private void handleConnections() {
        for (String node : this.selector.connected()) {
            log.debug("Completed connection to node {}", node);
            this.connectionStates.connected(node);
        }
    }

    /**
     * Validate that the response corresponds to the request we expect or else explode
     */
    private static void correlate(RequestHeader requestHeader, ResponseHeader responseHeader) {
        if (requestHeader.correlationId() != responseHeader.correlationId())
            throw new IllegalStateException("Correlation id for response (" + responseHeader.correlationId()
                    + ") does not match request (" + requestHeader.correlationId() + "), request header: " + requestHeader);
    }

    /**
     * Initiate a connection to the given node
     */
    private void initiateConnect(Node node, long now) {
        String nodeConnectionId = node.idString();
        try {
            log.debug("Initiating connection to node {} at {}:{}.", node.id(), node.host(), node.port());
            this.connectionStates.connecting(nodeConnectionId, now);
            //TODO 尝试建立连接
            selector.connect(nodeConnectionId,
                             new InetSocketAddress(node.host(), node.port()),
                             this.socketSendBuffer,
                             this.socketReceiveBuffer);
        } catch (IOException e) {
            /* attempt failed, we'll try again after the backoff */
            connectionStates.disconnected(nodeConnectionId, now);
            /* maybe the problem is our metadata, update it */
            metadataUpdater.requestUpdate();
            log.debug("Error connecting to node {} at {}:{}:", node.id(), node.host(), node.port(), e);
        }
    }

    class DefaultMetadataUpdater implements MetadataUpdater {

        /* the current cluster metadata */
        private final Metadata metadata;

        /* true iff there is a metadata request that has been sent and for which we have not yet received a response */
        private boolean metadataFetchInProgress;

        /* the last timestamp when no broker node is available to connect */
        private long lastNoNodeAvailableMs;

        DefaultMetadataUpdater(Metadata metadata) {
            this.metadata = metadata;
            this.metadataFetchInProgress = false;
            this.lastNoNodeAvailableMs = 0;
        }

        @Override
        public List<Node> fetchNodes() {
            return metadata.fetch().nodes();
        }

        @Override
        public boolean isUpdateDue(long now) {
            return !this.metadataFetchInProgress && this.metadata.timeToNextUpdate(now) == 0;
        }

        @Override
        public long maybeUpdate(long now) {
            // should we update our metadata?
            long timeToNextMetadataUpdate = metadata.timeToNextUpdate(now);
            long timeToNextReconnectAttempt = Math.max(this.lastNoNodeAvailableMs + metadata.refreshBackoff() - now, 0);
            long waitForMetadataFetch = this.metadataFetchInProgress ? Integer.MAX_VALUE : 0;
            // if there is no node available to connect, back off refreshing metadata
            long metadataTimeout = Math.max(Math.max(timeToNextMetadataUpdate, timeToNextReconnectAttempt),
                    waitForMetadataFetch);

            if (metadataTimeout == 0) {
                // Beware that the behavior of this method and the computation of timeouts for poll() are
                // highly dependent on the behavior of leastLoadedNode.
                Node node = leastLoadedNode(now);
                //TODO 这个里面会封装请求。
                maybeUpdate(now, node);
            }

            return metadataTimeout;
        }

        @Override
        public boolean maybeHandleDisconnection(ClientRequest request) {
            ApiKeys requestKey = ApiKeys.forId(request.request().header().apiKey());

            if (requestKey == ApiKeys.METADATA && request.isInitiatedByNetworkClient()) {
                Cluster cluster = metadata.fetch();
                if (cluster.isBootstrapConfigured()) {
                    int nodeId = Integer.parseInt(request.request().destination());
                    Node node = cluster.nodeById(nodeId);
                    if (node != null)
                        log.warn("Bootstrap broker {}:{} disconnected", node.host(), node.port());
                }

                metadataFetchInProgress = false;
                return true;
            }

            return false;
        }

        @Override
        public boolean maybeHandleCompletedReceive(ClientRequest req, long now, Struct body) {
            short apiKey = req.request().header().apiKey();
            if (apiKey == ApiKeys.METADATA.id && req.isInitiatedByNetworkClient()) {
                //TODO 处理响应
                handleResponse(req.request().header(), body, now);
                return true;
            }
            return false;
        }

        @Override
        public void requestUpdate() {
            this.metadata.requestUpdate();
        }

        private void handleResponse(RequestHeader header, Struct body, long now) {
            this.metadataFetchInProgress = false;
            //因为服务端发送回来的是一个二进制的一个数据结构
            //所以生产者这儿要对这个数据结构要进行解析
            //解析完了以后就封装成一个MetadataResponse对象。
            MetadataResponse response = new MetadataResponse(body);
            //响应里面会带回来元数据的信息
            //获取到了从服务端拉取的集群的元数据信息。
            Cluster cluster = response.cluster();
            // check if any topics metadata failed to get updated
            Map<String, Errors> errors = response.errors();
            if (!errors.isEmpty())
                log.warn("Error while fetching metadata with correlation id {} : {}", header.correlationId(), errors);

            // don't update the cluster if there are no valid nodes...the topic we want may still be in the process of being
            // created which means we will get errors and no nodes until it exists
            //如果正常获取到了元数据的信息
            if (cluster.nodes().size() > 0) {
                //更新元数据信息。
                this.metadata.update(cluster, now);
            } else {
                log.trace("Ignoring empty metadata response with correlation id {}.", header.correlationId());
                this.metadata.failedUpdate(now);
            }
        }

        /**
         * Create a metadata request for the given topics
         */
        private ClientRequest request(long now, String node, MetadataRequest metadata) {
            RequestSend send = new RequestSend(node, nextRequestHeader(ApiKeys.METADATA), metadata.toStruct());
            return new ClientRequest(now, true, send, null, true);
        }

        /**
         * Add a metadata request to the list of sends if we can make one
         */
        private void maybeUpdate(long now, Node node) {
            if (node == null) {
                log.debug("Give up sending metadata request since no node is available");
                // mark the timestamp for no node available to connect
                this.lastNoNodeAvailableMs = now;
                return;
            }
            String nodeConnectionId = node.idString();
            //判断网络连接是否应建立好
            //因为我们还没有学习kafka的网络，所以大家就认为这儿的网络是已经建立好了
            if (canSendRequest(nodeConnectionId)) {
                this.metadataFetchInProgress = true;
                MetadataRequest metadataRequest;
                //
                if (metadata.needMetadataForAllTopics())
                    //封装请求（获取所有topics）的元数据信息的请求。
                    //但是我们一般获取元数据的时候，只获取自己要发送消息的
                    //对应的topic的元数据的信息
                    metadataRequest = MetadataRequest.allTopics();
                else
                    //我们默认走的这儿的这个方法
                //就是拉取我们发送消息的对应的topic的方法
                    metadataRequest = new MetadataRequest(new ArrayList<>(metadata.topics()));
                //这儿就给我们创建了一个请求（拉取元数据的）
                ClientRequest clientRequest = request(now, nodeConnectionId, metadataRequest);
                log.debug("Sending metadata request {} to node {}", metadataRequest, node.id());
                //发送请求
                //至于里面的代码是怎么发送的？我们在分析kafka网络知识的时候在给大家讲解
                //这儿大家只需要知道，他会在这儿存储要发送的请求
                doSend(clientRequest, now);
            } else if (connectionStates.canConnect(nodeConnectionId, now)) {
                // we don't have a connection to this node right now, make one
                log.debug("Initialize connection to node {} for sending metadata request", node.id());
                initiateConnect(node, now);
                // If initiateConnect failed immediately, this node will be put into blackout and we
                // should allow immediately retrying in case there is another candidate node. If it
                // is still connecting, the worst case is that we end up setting a longer timeout
                // on the next round and then wait for the response.
            } else { // connected, but can't send more OR connecting
                // In either case, we just need to wait for a network event to let us know the selected
                // connection might be usable again.
                this.lastNoNodeAvailableMs = now;
            }
        }

    }

}
