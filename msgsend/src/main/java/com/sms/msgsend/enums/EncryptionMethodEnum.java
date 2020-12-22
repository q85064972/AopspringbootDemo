package com.sms.msgsend.enums;

/**
 * 加密方式
 * @author ${user}
 * @date 2020/12/20
 */
public enum EncryptionMethodEnum {
    /**
     * 001 aes
     */
    AESMethod("001", "AES"),
    /**
     *002  des
     */
    DESMethod("002", "DES"),
    /**
     * 003 md5
     */
    MD5Method("003","MD5")
    ;


    /**
     * 状态码
     */
    private String code;
    /**
     * 描述
     */
    private String value;

    EncryptionMethodEnum(String key, String value) {
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
