package com.sms.msgsend.aop;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sms.msgsend.constants.SystemConstant;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.exception.AccessControlException;
import com.sms.msgsend.pojo.domain.SmsAccessControl;
import com.sms.msgsend.service.impl.entityservice.SmsAccessControlService;

import com.sms.msgsend.utils.GetIpAddress;
import com.sms.msgsend.utils.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.sms.msgsend.constants.SendMsgConstant.*;


/**
 * desc 系统权限控制
 *
 * @author ${user}
 * @date 2020/12/18
 */
@Aspect
@Component
public class SystemAccessControl {

    private static final Logger logger = LoggerFactory.getLogger(SystemAccessControl.class);

    @Autowired
    SmsAccessControlService smsAccessControlService;

    @Autowired(required = false)
    private HttpServletRequest request;


    /**
     * desc 权限控制切点 使用注解
     * /sms/send/logs
     */
    @Pointcut("@annotation(com.sms.msgsend.annotation.SystemAccessControl)")
    public void SystemAccessAspect() {
    }

    /**
     * desc : 权限控制1
     *
     * @param joinPoint
     */
    @Around("SystemAccessAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws AccessControlException {

        Object[] objects = joinPoint.getArgs();
        logger.info("权限前置拦截！" + Arrays.toString(objects));
        JSONObject jsonObject = (JSONObject) objects[SystemConstant.ONE];
        JSONObject msg = jsonObject.getJSONObject(MESSAGES);

        // 得到发送机构信息
        String organization = msg.getStr(ORGANIZATION);
        SmsAccessControl smsAccessControl =  smsAccessControlService.findByOrganization(organization);
        if (smsAccessControl == null){
            logger.error("未找到对应访问机构权限");
            throw new AccessControlException(ResultCodeEnum.AccessControlError.getvalue());
        }

        // 发送方式校验
        String userType = String.valueOf(objects[SystemConstant.ZERO]);
        if (smsAccessControl.getSendMethod().indexOf(userType) == -1){
            logger.error("不支持的发送方式");
            throw new AccessControlException(ResultCodeEnum.AccessControlError.getvalue());
        }

        // ip地址校验
        String ipStr = GetIpAddress.get(request);
        if (smsAccessControl.getIp().indexOf(ipStr) == -1){
            logger.error("无ip访问权限");
            throw new AccessControlException(ResultCodeEnum.AccessControlError.getvalue());
        }

        // 解密
        JSONObject contents = getDecryptStr(smsAccessControl,msg);
        msg.set(CONTENTS , contents);
        objects[SystemConstant.ONE] = msg;

        // todo 手机号黑名单校验 未实现
        String mobilePhone = contents.getStr(RECVMOBILE);
        System.out.println("mobilePhone:" + mobilePhone);
        Object response ;
        try {
            // 带入参数
          response =  joinPoint.proceed(objects);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("[权限控制]系统处理方式异常");
            throw new RuntimeException(ResultCodeEnum.FAIL.getvalue());
        }
        return response;

    }

    /**
     * 报文体加密/验签
     * @param smsAccessControl
     * @param msg
     * @return
     */
    private JSONObject  getDecryptStr(SmsAccessControl smsAccessControl,JSONObject msg) {
        // 密钥校验
        String password = msg.getStr(PASSWORD);
        String contents = msg.getStr(CONTENTS).trim();
        String encryptionMethod = smsAccessControl.getEncryptionMethod();
        String encryptionKey = smsAccessControl.getPassword();
        // todo 测试用， 这块先做一个得到加密报文 解密转换
        String encryptStr = Util.encrypt(encryptionMethod, encryptionKey, contents);
        System.out.println("得到的加密报文:" + encryptStr);
        String decryptStr = Util.decrypt(encryptionMethod, encryptionKey, password, contents);
        return JSONUtil.parseObj(decryptStr);
    }


}
