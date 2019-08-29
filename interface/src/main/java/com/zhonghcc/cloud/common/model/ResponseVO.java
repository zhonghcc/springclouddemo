package com.zhonghcc.cloud.common.model;

import lombok.Data;

/**
 * Created by Chenzhi on 2019/8/29.
 */
@Data
public class ResponseVO<T> {
    boolean sucess;
    String desc;
    T data;
}