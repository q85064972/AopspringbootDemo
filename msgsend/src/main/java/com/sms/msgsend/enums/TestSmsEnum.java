package com.sms.msgsend.enums;

/**
 * @author ${user}
 * @date 2020/12/10
 */
public enum TestSmsEnum {
    /**
     *测试
     */
    TEST("test", "wend test");

    /**
     * 状态码
     */
    private String key;
    /**
     * 描述
     */
    private String value;

    TestSmsEnum (String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getkey() {
        return key;
    }

    public String getvalue() {
        return value;
    }
}
