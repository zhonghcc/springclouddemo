package com.zhonghcc.cloud.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient; //服务发现客户端

    @GetMapping(value = "/add")
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        List<String> serviceIds = discoveryClient.getServices();
        serviceIds.forEach(id ->{
            List<ServiceInstance> instanceList = discoveryClient.getInstances(id);
            instanceList.forEach(serviceInstance -> {
                log.info("/add, host:" + serviceInstance.getHost() + ", service_id:" + serviceInstance.getServiceId() );
            });
        });


        Integer r = a + b;

        return r;
    }

}
