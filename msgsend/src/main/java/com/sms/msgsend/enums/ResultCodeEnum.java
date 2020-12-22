package com.sms.msgsend.enums;

/**
 * desc 错误代码
 * @author ${user}
 * @date 2020/12/14
 */
public enum ResultCodeEnum {
    /**
     * 001 成功
     */
    SUCCESS("001", "成功"),
    /**
     *002  失败
     */
    FAIL("002", "失败"),
    /**
     * 003  报文读取异常
     */
    AbnormalMessageReading("003","报文读取异常"),
    /**
     * 004  权限控制错误
     */
    AccessControlError("004","权限校验错误"),
    /**
     * 005 加密错误
     */
    EncryptionError("005", "报文加密异常"),
    /**
     * 005 解密错误
     */
    DecryptionError("005", "报文解密异常"),
    /**
     *006 阿里云服务异常
     */
    AliyunServerError("006", "阿里云短信服务异常"),
    /**
     * 006 阿里云连接异常
     */
    AliyunClientError("006","阿里云连接异常")
    ;


    /**
     * 状态码
     */
    private String code;
    /**
     * 描述
     */
    private String value;

    ResultCodeEnum(String key, String value) {
        this.code = key;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getvalue() {
        return value;
    }
}
