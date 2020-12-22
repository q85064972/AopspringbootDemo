package com.sms.msgsend.enums;

/**
 * value : 阿里云发送短信参数
 * @author ${user}
 * @date 2020/12/10
 */
public enum AliyunSmsEnum {
    /**
     *地址
     */
    SYS_DOMAIN("SysDomain", "dysmsapi.aliyuncs.com"),
    /**
     *版本
     */
    SYS_VERSION("SysVersion", "2017-05-25"),
    /**
     *方法
     */
    SYS_ACTION("SysAction","SendSms"),
    /**
     *服务器
     */
    REGIONID("RegionId", "cn-hangzhou"),
    /**
     * 签名模板
     */
    SIGNNAME("SignName", "中科微服务"),
    /**
     * 配置Id
     */
    ACCESSKEY_ID("AccessKeyId","LTAI8nl9F8dFTPnJ"),
    /**
     * 配置密钥
     */
    ACCESSKEY_SECRET("AccessKeySecret","HMS45ImiSOjASuQDwuKO40xU50HjIs")
    ;


    /**
     * 状态码
     */
    private String key;
    /**
     * 描述
     */
    private String value;

    AliyunSmsEnum(String key, String value) {
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
