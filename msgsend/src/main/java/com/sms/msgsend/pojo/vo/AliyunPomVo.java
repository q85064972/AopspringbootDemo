package com.sms.msgsend.pojo.vo;

import lombok.Data;

/**
 * @author ${user}
 * @date 2020/12/10
 */
@Data
public class AliyunPomVo {

    private String sysDomain;
    private String sysVersion;
    private String sysAction;
    private String regionId;
    private String signName;
    private String accessKeyId;
    private String accessKeySecret;

    private String phoneNumbers;
    private String templateCode;
    private String templateParam;


}
