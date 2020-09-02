RPC协议是客户端和服务器端之间的通信接口，它定义了服务器端对外提供的服务接口

Hadoop 中 RPC 机制的实现都在 org.apache.hadoop.ipc 这个包里, 下面都将围绕这个包解读 Hadoop RPC 机制
1. RPC.getServer(Object instance, String bindAddress, int port, Configuration conf), 在Hadoop 1. 0中, 是这样创建一个 RPC.Server 对象的, 
那么, 在 Hadoop 2. 0中, 在 Server 端如何创建一个 RPC.Server 对象呢? 代码如下:  


interface LoginProtocol extends org.apache.hadoop.ipc.VersionedProtocol {
// 版本号，默认情况下，不同版本号的 RPC Client 和 Server 之间不能相互通信
// Hadoop 中所有自定义RPC接口都需要继承VersionedProtocol接口，它描述了协议的版本信息
public static final long versionID = 1L;
String echo(String value1,String value2) throws IOException;
}

public class RPCServer {
public static void main(String[] args)
throws HadoopIllegalArgumentException, IOException {
// 设置4个必需参数:
// setBindAddress("192.168.8.101") : Server端的IP地址
// setPort(1234) : 端口
// setProtocol(LoginProtocol.class) : setRPC协议接口的class对象
// setInstance(new LoginProtocolImpl()) : RPC协议接口的实现类的实例
RPC.Server server = new RPC.Builder(new Configuration())
.setBindAddress("192.168.8.101").setPort(1234)
.setProtocol(LoginProtocol.class)
.setInstance(new LoginProtocolImpl()).build();
server.start();
}
}
好的, 继续跟踪源码, 在 RPC 的内部类 Builder 中, 有一个 builder() 方法, 这应该是工厂模式
只要知道 RPC.Builder.builder() 这个方法的目的是构造一个 RPC.Server 实例对象
RPC$Builder.build() 方法源码如下


/**
* Build the RPC Server.
*/public Server build() throws IOException, HadoopIllegalArgumentException {
...
// getProtocolEngine() 获取一个RPC协议接口的引擎对象 WritableRPCEngine
// WritableRPCEngine.getServer() 通过WritableRPCEngine获取RPC.Server实例对象
return getProtocolEngine(this.protocol, this.conf).getServer(
this.protocol, this.instance, this.bindAddress, this.port,
this.numHandlers, this.numReaders, this.queueSizePerHandler,
this.verbose, this.conf, this.secretManager, this.portRangeConfig);
}
}
RPC$Builder.build() 方法最终会调用 WritableRpcEngine.getServer(Class<?>, Object, String, int, int, int, int, boolean, Configuration, SecretManager<TokenIdentifier>, String) 方法, 获取一个 RPC.Server实例对象, 
WritableRpcEngine.getServer() 源码如下:


/*
* Construct a server for a protocol implementation instance listening on a port and address.
*/@Overridepublic RPC.Server getServer(Class<?> protocolClass,
Object protocolImpl, String bindAddress, int port,
int numHandlers, int numReaders, int queueSizePerHandler,
boolean verbose, Configuration conf,
SecretManager<? extends TokenIdentifier> secretManager,
String portRangeConfig)
throws IOException {
// 创建一个RPC服务端代理对象server
// protocolClass: 被代理RPC协议接口( LoginProtocol )
// protocolImpl: 代理代理RPC协议接口的实现类( LoginProtocolImp )
// conf: 配置信息
// port: RPC服务端的监听端口
// numHandlers: RPC服务端Handler线程的数目
// ...
return new Server(protocolClass, protocolImpl, conf, bindAddress, port,
numHandlers, numReaders, queueSizePerHandler, verbose, secretManager,
portRangeConfig);
}

好的, 现在已经构造了一个 RPC.Server 的实例对象 server , 监听 Server 端的 "1234" 端口, Client 端只要调用代理RPC 代理对象( proxy )的方法( login() ), Server 端就会监听到这个方法调用, 并调用 Server 端RPC协议接口( LoginProtocol ) 的实现方法 LoginProtocolImp.login()
现在启动 Server 端, server.start(), 静候 Client 端的RPC请求,
现在来看看 Client 端吧!
2. Client 端获取 RPC 代理对象, LoginProtocol proxy = RPC.getProxy()


public class LoginClient {
public static void main(String[] args) throws IOException {
// getProxy()参数:
// LoginProtocol.class : RPC协议接口的class对象
// 1L : RPC协议接口的版本信息(versionID)
// new InetSocketAddress("192.168.8.101", 1234) : Server端的IP地址及端口
// conf : Configuration实例
LoginProtocol proxy = RPC.getProxy(LoginProtocol.class, 1L, new InetSocketAddress("192.168.8.101", 1234),
new Configuration());
String result = proxy.login("rpc", "xxx");
System.out.println(result);
}
}
Client 端如何获取一个RPC代理对象呢? 源码如下:
RPC.getProxy(Class<T>, long, InetSocketAddress, Configuration)方法如下:


/**
* Construct a client-side proxy object with the default SocketFactory
* @param <T>
* @param protocol
* @param clientVersion
* @param addr
* @param conf
* @return a proxy instance
* @throws IOException
*/public static <T> T getProxy(Class<T> protocol,
long clientVersion,
InetSocketAddress addr, Configuration conf)
throws IOException {
return getProtocolProxy(protocol, clientVersion, addr, conf).getProxy();
}
RPC.getProxy(Class<T>, long, InetSocketAddress, Configuration) 方法最终会调用   
 WritableRpcEngine.getProxy(Class<T>, long, InetSocketAddress, UserGroupInformation, Configuration, SocketFactory, int, RetryPolicy) 方法, 源码如下:


/** Construct a client-side proxy object that implements the named protocol,
* talking to a server at the named address.
* @param <T>
*/@Overridepublic <T> ProtocolProxy<T> getProxy(Class<T> protocol, long clientVersion,
InetSocketAddress addr, UserGroupInformation ticket,
Configuration conf, SocketFactory factory,
int rpcTimeout, RetryPolicy connectionRetryPolicy)
throws IOException {
...
// 构造Client端RPC代理对象( proxy )
// protocol.getClassLoader() : RPC协议接口LoginProtocol的类加载器
// new Class[] { protocol } : RPC协议接口LoginProtocol的接口对象
// new Invoker(protocol, addr, ticket, conf, factory, rpcTimeout) : InvocationHandler的实例对象
T proxy = (T) Proxy.newProxyInstance(protocol.getClassLoader(),
new Class[] { protocol }, new Invoker(protocol, addr, ticket, conf,
factory, rpcTimeout));
return new ProtocolProxy<T>(protocol, proxy, true);
}
使用 Proxy 类的静态方法 getProxy() 构造 Client 端 RPC 代理对象( proxy ), 
如何构造? Proxy.newProxyInstance() 方法需要3个参数: 
    1). RPC 协议接口 LoginProtocol 的类加载器, 
    2). RPC 协议接口 LoginProtocol 的 Class 对象, 
    3). WritableRpcEngine.Invoker 实例对象, WritableRpcEngine.Invoker 实现了 InvocationHandler 接口

3. 调用 proxy.login() 方法, 现在已经构造了一个 Client 端 RPC 代理对象( proxy ), 现在调用 RPC 代理对象( proxy )的 login() 方法, Client 端会发生什么呢?
调用 Proxy 实例的方法时, 都会被 InvocationHandler 实例对象的 invoke() 方法所捕获
先来看一下 WritableRpcEngine.Invoker 这个类


private static class Invoker implements RpcInvocationHandler {
private Client.ConnectionId remoteId; // 连接标识符
private Client client; // RPC客户端, 最重要的成员变量
private boolean isClosed = false;
...
@Override
public Object invoke(Object proxy, Method method, Object[] args) { // 最重要的方法
}
}
WritableRpcEngine.Invoker 的构造方法: 
WritableRpcEngine$Invoker.invoke(Object, Method, Object[]) 方法如下: 


public Object invoke(Object proxy, Method method, Object[] args)
throws Throwable {
...
// 调用RPC代理对象proxy的login()方法, Client端最终的方法调用在这里
ObjectWritable value = (ObjectWritable)
client.call(RPC.RpcKind.RPC_WRITABLE, new Invocation(method, args), remoteId);
...
return value.get();
}
先来看一下WritableRpcEngine.Invocation这个类, 在 Hadoop 1.0中是 RPC.Invocation


/** A method invocation, including the method name and its parameters */private static class Invocation implements Writable, Configurable {
private String methodName; // RPC代理对象调用的方法名
private Class<?>[] parameterClasses; // 方法的参数列表的Class对象数组
private Object[] parameters; // 方法的参数列表
...
private long clientVersion; // RPC协议接口的VersionID
private int clientMethodsHash; //
private String declaringClassProtocolName;
WritableRpcEngine.Invocation 的构造方法:
WritableRpcEngine$Invocation.<init>(Method, Object[])


public Invocation(Method method, Object[] parameters) {
this.methodName = method.getName(); // 获取RPC代理对象调用的方法名
this.parameterClasses = method.getParameterTypes(); // 获取方法的参数列表的Class对象数组
this.parameters = parameters; // 获取方法的参数列表
this.clientVersion = RPC.getProtocolVersion(method.getDeclaringClass()); // 获取RPC协议接口的VersionID
...
}

好的, 现在 Invocation 对象创建完成
再回去来看看 Client.call(RPC$RpcKind, Writable, Client$ConnectionId, int) 方法
Client.call(RPC$RpcKind, Writable, Client$ConnectionId, int) 这个方法, 首先根据输入参数 param( Invocation实例对象 )构造一个 Client.Call 实例对象. 再通过 getConnection() 方法获取 RPC 连接 connection, 再通过 connection.sendRpcReques(call) 方法把 RPC请求发送出去.


public Writable call(RPC.RpcKind rpcKind, Writable rpcRequest,
ConnectionId remoteId, int serviceClass) throws IOException {
final Call call = createCall(rpcKind, rpcRequest); // 创建一个Client.Call实例对象
Connection connection = getConnection(remoteId, call, serviceClass); // 获取Client.Connection实例
try {
connection.sendRpcRequest(call); // 通过connection发送RPC请求
} catch (Exception e) {
...
}

boolean interrupted = false;
synchronized (call) {
while (!call.done) {
try {
call.wait(); // 等待调用完成的返回结果
} catch (InterruptedException ie) {
interrupted = true; // 远程调用被打断
}
}
if (interrupted) {
Thread.currentThread().interrupt();
}

if (call.error != null) {
if (call.error instanceof RemoteException) { // 远程调用异常返回, 抛出异常给本地调用者
call.error.fillInStackTrace();
throw call.error;
} else { // 本地处理出现异常
InetSocketAddress address = connection.getRemoteAddress();
throw NetUtils.wrapException(address.getHostName(), address.getPort(), NetUtils.getHostname(),
0, call.error);
}
} else {
return call.getRpcResponse(); // 远程调用正常结束, 返回结果
}
}
}

RPC 请求发送出去后, Client 端开始等待( call.wait() ) Server 端发送回来的应答, Client.Call.wait() 方法必然有对应的 Client.Call.notify() 方法, 在 Client.Call.callComplete() 方法中调用 notify() 方法
问题又来了, Client.Call.callComplete() 方法何时被调用?
所以 Client.Call.callComplete() 方法最终是被 Client.Connection.receiveRpcResponse() 所调用, 如方法名receiveRpcResponse, 肯定是在 Client 端接收到了 Server 端的应答时被调用. 


