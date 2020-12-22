package com.sms.msgsend.pojo.model;

import com.sms.msgsend.enums.ResultCodeEnum;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS)
                .setMessage(ResultCodeEnum.SUCCESS)
                .setData(data);
    }

    public static Result genFailResult(ResultCodeEnum resultCodeEnum) {
        return new Result()
                .setCode(resultCodeEnum)
                .setMessage(resultCodeEnum);
    }

    public static Result genFailResult(ResultCodeEnum resultCodeEnum,String message) {
        return new Result()
                .setCode(resultCodeEnum)
                .setMessage(message);
    }
}
