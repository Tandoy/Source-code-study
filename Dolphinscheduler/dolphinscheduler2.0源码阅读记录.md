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
### 网络相关
```text
1.初始化远程服务器(这里主要涉及Netty相关知识)
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
        1.5.1 异步基于事件驱动的网络框架，线程模型：基于Reactor多线程做了一定改进。
```