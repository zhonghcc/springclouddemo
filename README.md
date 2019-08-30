# springclouddemo

#### 介绍

项目地址
https://github.com/zhonghcc/springclouddemo

项目模块说明：

- eureka：注册中心
- interface：feign接口和公共model
- backend：服务提供者
- surface：表面层，服务消费者

实现方案

- feign + protostuff
- 实现protostuff的HttpMessageConverter从而支持传输protobuf报文
- 实现了GenericHttpMessageConverter从而支持泛型
- 外层包装了通用信息格式以支持扩展（比如trace、服务间验证等）
- TODO: 尚未对httpclient进行定制


性能测试


1w
total cost =9032
9021
8309
8500

avg=8715

原始
10230
8838
9308
10136

avg=9628

加速9.4%
