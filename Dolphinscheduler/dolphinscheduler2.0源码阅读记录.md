## dolphinscheduler 2.0源码阅读记录

### 一、主要模块分为：Master、Worker、API、Logger、Alert
```text
概要：
	1.其中Master主要存在一个线程不断扫描Command表用来生成流程定义实例（DAG划分）、dispatcher任务、接收worker发回的任务状态进行监控、以及使用Quartz进行分布式定时调度实现
	2.其中Worker主要是接收master分发的任务根据不同的任务类型进行实际任务调度执行器
	3.其中API主要是与UI界面进行交互
	4.其中Logger主要是实时返回任务执行日志至UI界面
	5.其中Alert主要是进行任务告警以及SQL任务查询结果的发送
	6.并且都是通过zk进行注册，容错以及实现分布式锁
```
### 二、前提知识：网络相关（Netty）
```text
1.Netty相关知识
	1.1 TCP/IP --> Java原生IO（BIO） --> NIO/AIO --> Netty（Netty基于NIO）
	1.2 BIO 同步阻塞，每个连接请求服务器都会创建一个线程进行响应处理。适用于连接数目小且固定架构
    1.3 NIO 同步非阻塞，由Channel(通道)、Buffer(缓冲区)、Selector(选择器)组成，以块的方式处理数据，数据总是从通道读取到缓冲区或者从缓冲区写入通道中，Selector用于监听过个通道的事件，因此这样就可以达到每个服务器线程可以处理多个客户端连接请求。
            每个Channel对应一个Buffer；每个Selector对应一个服务器线程，一个Selector对应多个Channel；程序切换到哪个Channel是由事件触发决定的；
            Buffer实际上一个内存块；数据的读取写入都是经过Buffer，是双向的；Channel也是双向的。
        1.3.1 Buffer：其实就是含数组的对象容器，源码中重要的属性包括：mark(标记)、position(当前数据偏移量)、limit(读取以及写入最大值)、capacity(buffer容量)
        1.3.2 Channel：双向读写
        1.3.3 Selector：多个Channel以事件的方式注册到同一个Selector
    1.4 AIO 异步非阻塞，Netty基于NIO的，所以不赘述AIO。适用于连接数多且时间长的场景
    1.5 Netty 
        1.5.1 异步基于事件驱动的网络框架，线程模型：基于主从Reactor多线程做了一定改进。
        1.5.2 Netty主要角色分为：BossGroup、WorkerGroup、Pipeline(包含多个Handler进行处理业务逻辑)、Channel，每个Group都包含Selector、TaskQueue(用于解决handler中耗时处理逻辑)。
              BossGroup：用于接收客户端连接请求，WorkerGroup：专门处理读写请求
              每个Group都是NioEventLoop，每个NioEventLoop都有Selector用于监听对应事件从而进行给到不同的Handler进行处理
        1.5.3 Netty I/O操作都是异步的，Future-Listener机制：我们可以添加监听器，当监听的事件发生时，会通知监听器。
```
#### Netty编程实例
##### NettyServer
```java
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    
    public static void main(String[] args) throws Exception {
        
        //创建BossGroup 和 WorkerGroup
        //说明
        //1. 创建两个线程组 bossGroup 和 workerGroup
        //2. bossGroup 只是处理连接请求 , 真正的和客户端业务处理，会交给 workerGroup完成
        //3. 两个都是无限循环
        //4. bossGroup 和 workerGroup 含有的子线程(NioEventLoop)的个数
        //   默认实际 cpu核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //8
        
        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) //使用NioSocketChannel 作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
            //          .handler(null) // 该 handler对应 bossGroup , childHandler 对应 workerGroup
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化对象(匿名对象)
                        //给pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户socketchannel hashcode=" + ch.hashCode()); //可以使用一个集合管理 SocketChannel， 再推送消息时，可以将业务加入到各个channel 对应的 NIOEventLoop 的 taskQueue 或者 scheduleTaskQueue
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    }); // 给我们的workerGroup 的 EventLoop 对应的管道设置处理器

            System.out.println(".....服务器 is ready...");

            //绑定一个端口并且同步, 生成了一个 ChannelFuture 对象
            //启动服务器(并绑定端口)
            ChannelFuture cf = bootstrap.bind(6668).sync();

            //给cf 注册监听器，监控我们关心的事件

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口 6668 成功");
                    } else {
                        System.out.println("监听端口 6668 失败");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
```
##### NettyServerHandler
```java
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * 说明
 * 1. 我们自定义一个Handler 需要继续netty 规定好的某个HandlerAdapter(规范)
 * 2. 这时我们自定义一个Handler , 才能称为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际(这里我们可以读取客户端发送的消息)
    /**
     * 1. ChannelHandlerContext ctx:上下文对象, 含有 管道pipeline , 通道channel, 地址
     * 2. Object msg: 就是客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        
        System.out.println("服务器读取线程 " + Thread.currentThread().getName() + " channle =" + ctx.channel());
        System.out.println("server ctx =" + ctx);
        System.out.println("看看channel 和 pipeline的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline(); //本质是一个双向链接, 出站入站
        
        //将 msg 转成一个 ByteBuf
        //ByteBuf 是 Netty 提供的，不是 NIO 的 ByteBuffer.
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + channel.remoteAddress());
    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush 是 write + flush
        //将数据写入到缓存，并刷新
        //一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵1", CharsetUtil.UTF_8));
    }
    
    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
```
##### NettyClient
```java
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    
    public static void main(String[] args) throws Exception {

        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 设置客户端通道的实现类(反射)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler()); //加入自己的处理器
                        }
                    });
            
            System.out.println("客户端 ok..");
            //启动客户端去连接服务器端
            //关于 ChannelFuture 要分析，涉及到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
            //给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
```
```java
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    
    //当通道就绪就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server: (>^ω^<)喵", CharsetUtil.UTF_8));
    }

    //当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务器回复的消息:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址： " + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
```
[Netty学习手册](https://dongzl.github.io/netty-handbook/#/_content/chapter01)

### 三、Master
```text
Master主要存在一个线程不断扫描Command表用来生成流程定义实例（DAG划分）、dispatcher任务、接收worker发回的任务状态进行监控、以及使用Quartz进行分布式定时调度实现
```
#### Master启动流程分析(MasterServer.run())
##### 1.init remoting server
```java
// 1.初始化NettyServerConfig
// 其中包括：初始化server可连接队列大小1024、是否保持活跃、buffer的大小65535
NettyServerConfig serverConfig = new NettyServerConfig();
// 2.从master.properties中读取端口用来设置server端绑定端口
serverConfig.setListenPort(masterConfig.getListenPort());
// 3.初始化server
// 其中包括：根据操作系统(linux/mac)的不同使用不同方式创建bossGroup、workGroup;注意在创建workGroup时会使用当前系统的可用线程，这里与netty原生不同，netty默认会采用CPU核数*2来机械能初始化
this.nettyRemotingServer = new NettyRemotingServer(serverConfig);
TaskAckProcessor ackProcessor = new TaskAckProcessor();
// 4.任务实例状态响应
// TODO 待深入分析
ackProcessor.init(processInstanceExecMaps);
TaskResponseProcessor taskResponseProcessor = new TaskResponseProcessor();
taskResponseProcessor.init(processInstanceExecMaps);
StateEventProcessor stateEventProcessor = new StateEventProcessor();
stateEventProcessor.init(processInstanceExecMaps);
this.nettyRemotingServer.registerProcessor(CommandType.TASK_EXECUTE_RESPONSE, taskResponseProcessor);
this.nettyRemotingServer.registerProcessor(CommandType.TASK_EXECUTE_ACK, ackProcessor);
this.nettyRemotingServer.registerProcessor(CommandType.TASK_KILL_RESPONSE, new TaskKillResponseProcessor());
this.nettyRemotingServer.registerProcessor(CommandType.STATE_EVENT_REQUEST, stateEventProcessor);
this.nettyRemotingServer.start();
```
