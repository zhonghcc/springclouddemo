package com.zhonghcc.cloud.backend.controller;

import com.zhonghcc.cloud.backend.biz.OrderBiz;
import com.zhonghcc.cloud.backend.model.Order;
import com.zhonghcc.cloud.backend.service.OrderService;
import com.zhonghcc.cloud.common.model.ResponsePageVO;
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
    public ResponsePageVO<Order> getOrders() {
        log.info("getOrders");
        List<Order> list = orderBiz.getOrderList();
        ResponsePageVO<Order> responsePageVO = new ResponsePageVO<>();
        responsePageVO.setData(list);
        responsePageVO.setTotal(list.size());
        responsePageVO.setPage(0);
        responsePageVO.setPageSize(0);
        responsePageVO.setSucess(true);
        responsePageVO.setDesc("success");
        return responsePageVO;
    }

    @Override
    public ResponseVO<Order> update(Order order) {
        log.info("update {}",order);
        return null;
    }
}