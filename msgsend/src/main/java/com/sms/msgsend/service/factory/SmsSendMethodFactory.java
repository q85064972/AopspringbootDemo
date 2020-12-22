package com.sms.msgsend.service.factory;


import com.sms.msgsend.service.SmsSendMsgService;
import com.sms.msgsend.service.impl.sendmsg.AliyunSendMsgServiceImpl;
import com.sms.msgsend.service.impl.sendmsg.OtherSendMsgServiceImpl;

import static com.sms.msgsend.constants.SendMsgConstant.*;

/**
 * 发送短信的工厂类
 * @author ${user}
 * @date 2020/12/20
 */

public class SmsSendMethodFactory {

    /**
     * 短信发送方式
     */
    private String userType;

    public SmsSendMethodFactory(String userType) {
        this.userType = userType;

    }

    public SmsSendMsgService getSmsSendImpl(){
        if(userType == null){
            return null;
        }
        // 阿里云
        if(userType.equalsIgnoreCase(USERTYPE_ALIYUN)){
            return new AliyunSendMsgServiceImpl();
        }
        // other
        if(userType.equalsIgnoreCase(USERTYPE_OTHER)){
            return new OtherSendMsgServiceImpl();
        }
        return null;
   }


}
