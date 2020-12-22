package com.sms.msgsend.pojo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "sms_send_logs")
public class SmsSendLogs {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 发送时间
     */
    @Column(name = "sendTime")
    private String sendtime;

    /**
     * 状态
     */
    private String status;

    /**
     * 使用用户ID
     */
    private String userid;

    /**
     * 短信发送内容
     */
    private String sendmsg;

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
     * 请求内容
     */
    private String reqcontent;

    /**
     * 返回内容
     */
    private String rescontent;

}
