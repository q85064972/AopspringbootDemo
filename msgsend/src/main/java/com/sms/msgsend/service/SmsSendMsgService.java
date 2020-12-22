package com.sms.msgsend.service;


import cn.hutool.json.JSONObject;


/**
 * desc 发送给短信的方法
 *  注意这个类是工厂方式实现的不能 @Autowired
 * @author ${user}
 * @date 2020/12/14
 */
public interface SmsSendMsgService {

    /**
     * 发送短信
     * @param jsonObject
     * @return
     */
     JSONObject sendMsg(JSONObject jsonObject);

}
