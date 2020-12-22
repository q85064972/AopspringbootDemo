package com.sms.msgsend.controller;

import com.sms.msgsend.annotation.ReqSystemLogAnnotation;
import com.sms.msgsend.pojo.model.Result;
import com.sms.msgsend.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * desc 发送短信的controller
 *
 * @author ${user}
 * @date 2020/12/14
 */
@Api(value = "/sms", tags = "短信发送")
@RestController
@RequestMapping("/sms")
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    SmsService smsService;

    /**
     *
     * desc send MSG 发送短信
     *使用了模板方法模式
     * @return
     */
    @ApiOperation(value = "发送短信", notes = "发送短信", httpMethod = "GET")
    @GetMapping("/sendMsg{userType}")
    public Result sendMsgUseTemplateMethod(@PathVariable("userType") String userType, @RequestBody String shortMessageVO) {
        // 使用模板方式发送短信
        return smsService.smsSend(userType,shortMessageVO);
    }

}
