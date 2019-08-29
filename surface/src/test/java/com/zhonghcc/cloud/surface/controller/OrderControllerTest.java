package com.zhonghcc.cloud.surface.controller;

import com.zhonghcc.cloud.backend.model.Order;
import com.zhonghcc.cloud.backend.service.Order2Service;
import com.zhonghcc.cloud.backend.service.OrderService;
import com.zhonghcc.cloud.common.model.ResponsePageVO;
import com.zhonghcc.cloud.serface.SerfaceApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class OrderControllerTest extends SerfaceApplicationTests {
    @Autowired
    OrderService orderService;

    @Autowired
    Order2Service order2Service;

    @Test
    public void getList() {
        ResponsePageVO<Order> responsePageVO = orderService.getOrders();

        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            orderService.getOrders();
        }
        long end = System.currentTimeMillis();

        System.out.println("total cost="+(end-start));
        System.out.println(responsePageVO);


    }
}