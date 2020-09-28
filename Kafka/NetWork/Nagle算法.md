Nagle算法主要是避免发送小的数据包，要求TCP连接上最多只能有一个未被确认的小分组，
在该分组的确认到达之前不能发送其他的小分组。相反，TCP收集这些少量的小分组，并在确认到来时以一个分组的方式发出去。


 
if there is new data to send
  if the window size >= MSS and available data is >= MSS
    send complete MSS segment now
  else
    if there is unconfirmed data still in the pipe
      enqueue data in the buffer until an acknowledge is received
    else
      send data immediately
    end if
  end if
end if


从上述算法中看出：
1. 对于MSS的片段直接发送

2. 如果有没有被确认的data在缓冲区内，先将待发送的数据放到buffer中直到被发送的数据被确认【最多只能有一个未被确认的小分组】

3. 两种情况置位，就直接发送数据，实际上如果小包，但是没有未被确认的分组，就直接发送数据。

这里通过一个实验来看下Nagle算法对于发送的优化：

实验要求：Client端每次发送1个字节，将hello发送到Server端，然后server再全部发送给Client，其实要点在于Client的发送，预期的结果是：

1. 我们虽然一个字节一个字节的发，但是在协议中使用Nagle算法，可能会有延时等待的状况，即将几个字符合成一个片段进行发送

2. 必须是收到对方的确认之后，才能再次发送


禁用Nagle算法
在默认的情况下,Nagle算法是默认开启的，Nagle算法比较适用于发送方发送大批量的小数据，并且接收方作出及时回应的场合，这样可以降低包的传输个数。同时协议也要求提供一个方法给上层来禁止掉Nagle算法

当你的应用不是连续请求+应答的模型的时候，而是需要实时的单项的发送数据并及时获取响应，这种case就明显不太适合Nagle算法，明显有delay的。

linux提供了TCP_NODELAY的选项来禁用Nagle算法。

禁用方法：

setsockopt(client_fd, SOL_TCP, TCP_NODELAY,(int[]){1}, sizeof(int));

1. 禁止Nagle算法，每一次send，都会组一个包进行发送，HELLO被分成5个小包分别发送

2.不用等待ACK，可以连续发送