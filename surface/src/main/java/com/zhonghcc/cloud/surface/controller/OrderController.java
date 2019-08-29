package com.zhonghcc.cloud.surface.controller;

import com.zhonghcc.cloud.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order")
public class OrderController{

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getList")
    public @ResponseBody String getList(){
        return orderService.getOrders().toString();
    }

}
