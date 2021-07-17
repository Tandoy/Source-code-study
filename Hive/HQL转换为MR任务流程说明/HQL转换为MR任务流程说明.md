### HQL转换为MR任务流程说明
```text
1.进入程序，利用Antlr框架定义HQL的语法规则，对HQL完成词法语法解析，将HQL转换为为AST（抽象语法树）；
2.遍历AST，抽象出查询的基本组成单元QueryBlock（查询块），可以理解为最小的查询执行单元；
3.遍历QueryBlock，将其转换为OperatorTree（操作树，也就是逻辑执行计划），可以理解为不可拆分的一个逻辑执行单元；
4.使用逻辑优化器对OperatorTree（操作树）进行逻辑优化。例如合并不必要的ReduceSinkOperator，减少Shuffle数据量；
5.遍历OperatorTree，转换为TaskTree。也就是翻译为MR任务的流程，将逻辑执行计划转换为物理执行计划；
6.使用物理优化器对TaskTree进行物理优化；
7.生成最终的执行计划，提交任务到Hadoop集群运行。
```