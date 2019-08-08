package com.zhonghcc.cloud.model;

import lombok.Data;

@Data
public class Order {
    long instructionId;
    String name;
    String desc;
    long createTime;
    long updateTime;
}
