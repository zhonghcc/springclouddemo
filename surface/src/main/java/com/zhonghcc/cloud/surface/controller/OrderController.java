package com.zhonghcc.cloud.surface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/getList")
    public @ResponseBody String getList(){
        return "{}";
    }

}
