package com.zhonghcc.cloud.common.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Chenzhi on 2019/8/29.
 */
@Data
public class ResponsePageVO<T> {
    boolean sucess;
    String desc;
    List<T> data;
    int page;
    int total;
    int pageSize;
}