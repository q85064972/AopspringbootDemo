package com.sms.msgsend.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.sms.msgsend.annotation.ReqSystemLogAnnotation;
import com.sms.msgsend.annotation.ResSystemLogAnnotation;
import com.sms.msgsend.annotation.SystemAccessControl;

import com.sms.msgsend.constants.SendMsgConstant;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.pojo.model.Result;
import com.sms.msgsend.pojo.model.ResultGenerator;
import com.sms.msgsend.service.SmsSendMsgService;
import com.sms.msgsend.service.SmsService;
import com.sms.msgsend.service.factory.SmsSendMethodFactory;
import com.sms.msgsend.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * desc 发送短信的实体类
 *  放弃此方式 改用模板方式模式实现
 * @author ${user}
 * @date 2020/12/14
 */
@Service
public class SmsServiceImpl implements SmsService{

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * desc 模板方法模式控制发送短信的总流程
     */
    @Override
    public final Result smsSend(String userType, String shortMessageVo){
        if (StrUtil.isBlank(userType)) {
            userType = SendMsgConstant.USERTYPE_ALIYUN;
        }
        // 得到短信内容
        JSONObject msgStr = getMsg(shortMessageVo);
        if (msgStr == null) {
            logger.error("无效的报文类型");
            return ResultGenerator.genFailResult(ResultCodeEnum.AbnormalMessageReading);
        }
        // 将报文转换成json格式
        logger.info("得到的jsonObject:{}", msgStr.toString());
        // 发送短信
        JSONObject resultStr =  sendMsg(userType,msgStr);
        logger.info("得到的result:" + resultStr.toString());
        // 返回结果
        return  handleMsg(resultStr);
    }


    @ReqSystemLogAnnotation(description = "请求日志记录")
    JSONObject getMsg(String shortMessageVO) {
        return Util.getJsonMsg(shortMessageVO);
    }

    @SystemAccessControl(description = "发送短信权限控制")
    JSONObject sendMsg(String userType, JSONObject jsonObject) {
        // aop 权限控制/ 解密 aop已经做完
        logger.info("得到的报文:" + jsonObject.toString());
        // 工厂模式找到发送短信的方法
        SmsSendMethodFactory smsSendMethodFactory = new SmsSendMethodFactory(userType);
        SmsSendMsgService smsSendMsgService = smsSendMethodFactory.getSmsSendImpl();
        return smsSendMsgService.sendMsg(jsonObject);
    }

    @ResSystemLogAnnotation(description = "响应日志记录")
    Result handleMsg(JSONObject result) {
        // 发送成功
        if (ResultCodeEnum.SUCCESS.getCode().equals(result.get(SendMsgConstant.RESULT))) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(ResultCodeEnum.FAIL, result.getStr(SendMsgConstant.MESSAGE));
        }
    }


}
