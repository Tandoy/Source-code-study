1. Server.Listener
RPC Client 端的 RPC 请求发送到 Server 端后, 首先由 Server.Listener 接收
Server.Listener 类继承自 Thread 类, 监听了 OP_READ 和 OP_ACCEPT 事件
Server.Listener 接收 RPC 请求, 在 Server.Listener.doRead() 方法中读取数据, 在 doRead() 方法中又调用了Server.Connection.readAndProcess() 方法, 
最后会调用 Server.Connection.processRpcRequest() 方法, 源码如下:
private void processRpcRequest(RpcRequestHeaderProto header,
        DataInputStream dis) throws WrappedRpcServerException,
        InterruptedException {
      ...
      Writable rpcRequest;
      // 从成员变量dis中反序列化出Client端发送来的RPC请求( WritableRpcEngine.Invocation对象 )
      try { //Read the rpc request
        rpcRequest = ReflectionUtils.newInstance(rpcRequestClass, conf);
        rpcRequest.readFields(dis);
      } catch (Throwable t) { // includes runtime exception from newInstance
        ...
      }
      // 构造Server端Server.Call实例对象
      Call call = new Call(header.getCallId(), header.getRetryCount(),
          rpcRequest, this, ProtoUtil.convert(header.getRpcKind()), header
              .getClientId().toByteArray());
      // 将Server.Call实例对象放入调用队列中
      callQueue.put(call);              // queue the call; maybe blocked here
      incRpcCount();  // Increment the rpc count
    }
调用队列 callQueue 是 Server 的成员变量, Server.Listener 和 Server.Handler 是典型的生产者, 消费者模型, 
Server.Listener( 生产者 )的doRead()方法最终调用Server.Connection.processRpcRequest() 方法, 
而Server.Handler( 消费者 )处理RPC请求

2. Server.Handler 继承 Thread 类, 其主要工作是处理 callQueue 中的调用, 都在 run() 方法中完成. 在 run() 的主循环中, 每次处理一个从 callQueue 中出队的请求, Server.call() 是一个抽象方法, 实际是调用了 RPC.Server.call()方法, 最后通过 WritableRPCEngine.call() 方法完成 Server 端方法调用
/** Handles queued calls . */
  private class Handler extends Thread {
    ...
    @Override
    public void run() {
      ...
      ByteArrayOutputStream buf = 
        new ByteArrayOutputStream(INITIAL_RESP_BUF_SIZE);
      while (running) {
          ...
          final Call call = callQueue.take();    // 获取一个RPC调用请求
          ...
          Writable value = null;

          value = call.connection.user.doAs(new PrivilegedExceptionAction<Writable>() {
                     @Override
                     public Writable run() throws Exception {
                       // 调用RPC.Server.call()方法
                       // call.rpcKind : RPC调用请求的类型, 一般为Writable
                       // call.connection.protocolName : RPC协议接口的类名
                       // call.rpcRequest : Invocation实例对象, 包括方法名, 参数列表, 参数列表的Class对象数组
                       // call.timestamp : 调用时间戳
                       return call(call.rpcKind, call.connection.protocolName, 
                                   call.rpcRequest, call.timestamp);
                     }
                   });
      }
      ...
    }
}
RPC.Server.call() 方法如下:
@Overridepublic Writable call(RPC.RpcKind rpcKind, String protocol,
        Writable rpcRequest, long receiveTime) throws Exception {
  return getRpcInvoker(rpcKind).call(this, protocol, rpcRequest,
          receiveTime);
}
最后通过 WritableRPCEngine.call() 方法完成 Server 端方法调用, 代码如下:
@Overridepublic Writable call(org.apache.hadoop.ipc.RPC.Server server,
          String protocolName, Writable rpcRequest, long receivedTime)
          throws IOException, RPC.VersionMismatch {

        Invocation call = (Invocation)rpcRequest;       // 将RPC请求强制转成WritableRpcEngine.Invocation对象
        ...

        long clientVersion = call.getProtocolVersion();
        final String protoName;
        ProtoClassProtoImpl protocolImpl;       // Server端RPC协议接口的实现类的实例对象
        ...
        // Invoke the protocol method
    try {
          ...
          // 获取RPC请求中调用的方法对象Method
          Method method = 
                  protocolImpl.protocolClass.getMethod(call.getMethodName(),
                  call.getParameterClasses());
          method.setAccessible(true);
          ...
          // 在Server端RPC协议接口的实现类的实例对象 protocolImpl 上调用具体的方法
          Object value = 
                  method.invoke(protocolImpl.protocolImpl, call.getParameters());
          ...
          // 调用正常结束, 返回调用结果
          return new ObjectWritable(method.getReturnType(), value);

        } catch (InvocationTargetException e) { // 调用出现异常, 用IOException包装异常, 最后抛出该异常
          Throwable target = e.getTargetException();
          if (target instanceof IOException) {
                throw (IOException)target;
          } else {
                IOException ioe = new IOException(target.toString());
                ioe.setStackTrace(target.getStackTrace());
                throw ioe;
          }
        } catch (Throwable e) {
          ...
        }
  }
}
在 WritableRpcEngine.call() 方法中, 传入的 rpcRequest 会被强制转换成 WritableRpcEngine.Invocation 类型的对象 call , 并通过 call 这个对象包含的方法名(getMethodName()方法)和参数列表的 Class对象数组(getParameterClasses())获取 Method 对象, 最终通过 Method 对象的invoke() 方法, 调用实现类的实例对象 protocolImpl 上的方法, 完成 Hadoop 的远程过程调用

好了, 现在 Server 端的具体方法已经被调用了, 调用结果分两种情况:
    1) 调用正常结束, 则将方法的返回值和调用结果封装成一个 ObjectWritable 类型的对象, 并返回
    2) 调用出现异常, 抛出 IOException 类型的异常

3. Server.Responder
这个类的功能: 发送 Hadoop 远程过程调用的应答给 Client 端, Server.Responder 类继承自 Thread 类, 监听了 OP_WRITE 事件, 即通道可写.  具体细节写不下去了

总结: 
    Server.Responder 和 Server.Listener, Server.Handler 一起配合, 完成 Hadoop 中 RPC 的 Server 端处理:
Server.Listener 接收 Client 端的连接请求和请求数据; Server.Handler 完成实际的过程调用; Server.Responder 则进行应答发送
