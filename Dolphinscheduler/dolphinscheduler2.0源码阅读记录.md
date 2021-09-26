## dolphinscheduler 2.0源码阅读记录：

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
### Master
```text
1.初始化远程服务器(这里主要涉及Netty相关知识)
	1.1 Netty：TCP/IP --> Java原生IO（BIO） --> NIO/AIO --> Netty（Netty基于NIO）
	1.2 BIO 同步阻塞，每个连接请求服务器都会创建一个线程进行响应处理。适用于连接数目小且固定架构
```