package com.sms.msgsend.pojo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "sms_access_control")
public class SmsAccessControl {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构代码
     */
    @Column(name = "organization_code")
    private String organizationCode;

    /**
     * 机构名称
     */
    @Column(name = "organization_name")
    private String organizationName;

    /**
     * 密钥
     */
    private String password;

    /**
     * 加密方式
     */
    @Column(name = "encryption_method")
    private String encryptionMethod;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Integer createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private Integer updateBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private String createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private String updateDate;

    /**
     * 发送方式
     */
    @Column(name = "send_method")
    private String sendMethod;

    /**
     * 单独支持的ip地址
     */
    private String ip;

}
