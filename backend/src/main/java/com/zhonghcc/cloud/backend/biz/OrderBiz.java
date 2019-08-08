package com.zhonghcc.cloud.backend.biz;

import com.zhonghcc.cloud.model.Order;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBiz {

    public List<Order> getOrderList(){

        List<Order> list = new ArrayList<>();
        Order order = new Order();
        order.setInstructionId(1);

        order.setName("第一个订单");
        order.setDesc("第一个订单");
        order.setCreateTime(new DateTime().getMillis());
        order.setUpdateTime(new DateTime().getMillis());

        list.add(order);

        order = new Order();
        order.setInstructionId(2);

        order.setName("第二个订单");
        order.setDesc("第二个订单");
        order.setCreateTime(new DateTime().getMillis());
        order.setUpdateTime(new DateTime().getMillis());

        list.add(order);
        return list;

    }
}
