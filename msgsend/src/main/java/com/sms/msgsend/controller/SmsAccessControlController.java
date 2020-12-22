package com.sms.msgsend.controller;

import com.sms.msgsend.pojo.model.Result;
import com.sms.msgsend.pojo.model.ResultGenerator;
import com.sms.msgsend.pojo.domain.SmsAccessControl;
import com.sms.msgsend.service.impl.entityservice.SmsAccessControlService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
* desc : SmsAccessControlController
* @author wend
* @date 2020/12/18
*/
@RestController
@RequestMapping("/sms/access/control")
public class SmsAccessControlController {
    @Resource
    private SmsAccessControlService smsAccessControlService;

    @PostMapping
    public Result add(@RequestBody SmsAccessControl smsAccessControl) {
        smsAccessControlService.save(smsAccessControl);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        smsAccessControlService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody SmsAccessControl smsAccessControl) {
        smsAccessControlService.update(smsAccessControl);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SmsAccessControl smsAccessControl = smsAccessControlService.findById(id);
        return ResultGenerator.genSuccessResult(smsAccessControl);
    }


}
