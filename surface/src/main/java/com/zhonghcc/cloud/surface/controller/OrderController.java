package com.zhonghcc.cloud.surface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order")
public class OrderController{

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getList")
    public @ResponseBody String getList(){
        return restTemplate.getForEntity("http://backend/add?a=10&b=10",String.class).getBody();
    }

}
