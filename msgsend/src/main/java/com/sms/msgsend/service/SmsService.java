package com.sms.msgsend.service;

import com.sms.msgsend.pojo.model.Result;

/**
 * @author ${user}
 * @date 2020/12/20
 */
public interface SmsService {

    /**
     *  模板方式封装了 发送短信的总流程
     * @param userType
     * @param shortMessageVo
     * @return
     */
    Result smsSend(String userType, String shortMessageVo);

}
