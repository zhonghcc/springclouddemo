package com.zhonghcc.cloud.backend.service;

import com.zhonghcc.cloud.backend.model.Order;
import com.zhonghcc.cloud.common.model.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Chenzhi on 2019/8/29.
 */
@FeignClient("backend")
public interface OrderService {

    @RequestMapping(method = RequestMethod.GET, value = "/getOrders")
    List<Order> getOrders();

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    ResponseVO<Order> update(Order order);
}