package com.zhonghcc.cloud.backend.controller;

import com.zhonghcc.cloud.backend.biz.OrderBiz;
import com.zhonghcc.cloud.backend.model.Order;
import com.zhonghcc.cloud.backend.service.OrderService;
import com.zhonghcc.cloud.common.model.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Chenzhi on 2019/8/29.
 */
@RestController
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderBiz orderBiz;
    @Override
    public List<Order> getOrders() {
        log.info("getOrders");
        return orderBiz.getOrderList();
    }

    @Override
    public ResponseVO<Order> update(Order order) {
        log.info("update {}",order);
        return null;
    }
}