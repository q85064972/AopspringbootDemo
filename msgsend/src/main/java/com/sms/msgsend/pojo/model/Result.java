package com.sms.msgsend.pojo.model;

import com.alibaba.fastjson.JSON;
import com.sms.msgsend.enums.ResultCodeEnum;

/**
 * 统一API响应结果封装
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public Result setCode(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(ResultCodeEnum resultCodeEnum) {
        this.message = resultCodeEnum.getvalue();
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
