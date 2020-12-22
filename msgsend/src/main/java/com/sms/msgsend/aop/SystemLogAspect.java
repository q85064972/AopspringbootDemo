package com.sms.msgsend.aop;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.json.JSONObject;
import com.sms.msgsend.constants.SendMsgConstant;
import com.sms.msgsend.constants.SystemConstant;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.pojo.domain.SmsSendLogs;
import com.sms.msgsend.service.impl.entityservice.SmsSendLogsService;
import com.sms.msgsend.utils.GetIpAddress;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

import static com.sms.msgsend.constants.SendMsgConstant.*;

/**
 * 系统日志切点类
 *
 * @author linrx
 */
@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired(required = false)
    private HttpServletRequest request;

    /**
     * 短信日志对象
     */
    @Resource
    private SmsSendLogsService smsSendLogsService;

    /**
     * todo 这部分会不会有风险
     * 日志对象
     */
    private static SmsSendLogs smsSendLogs;




    @Pointcut("@annotation(com.sms.msgsend.annotation.ReqSystemLogAnnotation)")
    public void ReqMsgSendControllerAspectByAnnotation() {
    }

    @Pointcut("@annotation(com.sms.msgsend.annotation.ResSystemLogAnnotation)")
    public void ResMsgSendControllerAspectByAnnotation() {
    }

    /**
     * desc : 前置通知
     * @param joinPoint
     */
    @Before("ReqMsgSendControllerAspectByAnnotation()")
    public void doBefore(JoinPoint joinPoint) {

        Object[] objects = joinPoint.getArgs();
        logger.info("请求日志前置拦截！" + Arrays.toString(objects));
        // 记录请求日志
        smsSendLogs =new SmsSendLogs();
        // 请求时间
        smsSendLogs.setCreateDate(DateUtil.formatDate(new Date()));
        // ip地址
        String ipStr = GetIpAddress.get(request);
        logger.info("ip地址：{}",ipStr);
        long ip = NetUtil.ipv4ToLong(ipStr);
        smsSendLogs.setCreateBy((int) ip);
        // 请求报文
        smsSendLogs.setReqcontent(String.valueOf(objects[SystemConstant.ZERO]));

        smsSendLogsService.save(smsSendLogs);


    }

    /**
     * 后置通知 :用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Around("ResMsgSendControllerAspectByAnnotation()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        logger.info("响应日志前置拦截！" + Arrays.toString(objects));
        JSONObject resultStr = (JSONObject) objects[SystemConstant.ZERO];
        String result = resultStr.getStr(SendMsgConstant.RESULT);
        // 记录结果 0  没有问题， 1 有问题
        if (ResultCodeEnum.SUCCESS.getCode().equals(result)){
            smsSendLogs.setStatus(String.valueOf(SystemConstant.ZERO));
        }else{
            smsSendLogs.setStatus(String.valueOf(SystemConstant.ONE));
        }
        // 记录发送时间
        smsSendLogs.setSendtime(DateUtil.formatTime(new Date()));
        // 记录手机号
        smsSendLogs.setTelphone(resultStr.getStr(RECVMOBILE));
        // userId 将其作为一个查询主键
        smsSendLogs.setUserid(resultStr.getStr(USER_ID));
        // 发送内容
        smsSendLogs.setSendmsg(resultStr.getStr(MESSAGE_CONTENT));
        Object response ;
        try {
            response = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("[日志记录]处理方式异常");
            throw new RuntimeException(ResultCodeEnum.FAIL.getvalue());
        }
        // 返回报文
        smsSendLogs.setRescontent(response.toString());

        smsSendLogsService.update(smsSendLogs);
        return response;
    }

}
