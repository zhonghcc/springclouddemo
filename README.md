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

测试报文

protostuff
```
??.com.zhonghcc.cloud.common.model.ResponsePageVOsuccess[?&com.zhonghcc.cloud.backend.model.Order绗竴涓鍗?绗竴涓鍗?欺鐓?(谯鐓?[?&com.zhonghcc.cloud.backend.model.Order绗簩涓鍗?绗簩涓鍗?谯鐓?(谯鐓?  (0 $cb785f68-adf9-4dcf-928e-5e99dbfdbd2e
```
json
```
{"sucess":true,"desc":"success","data":[{"instructionId":1,"name":"第一个订单","desc":"第一个订单","createTime":1567138997473,"updateTime":1567138997492},{"instructionId":2,"name":"第二个订单","desc":"第二个订单","createTime":1567138997492,"updateTime":1567138997492}],"page":0,"total":2,"pageSize":0}
```

message标准封装

```
@Data
public class CoolMessage {
    Object data;//实际报文
    String uuid;//traceid
    String token;//authid
}
```

支持泛型传递

```
@Data
public class ResponsePageVO<T> {
    boolean sucess;
    String desc;
    List<T> data;
    int page;
    int total;
    int pageSize;
}
```

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
