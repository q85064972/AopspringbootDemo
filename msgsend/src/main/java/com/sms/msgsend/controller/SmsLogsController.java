package com.sms.msgsend.controller;

import com.sms.msgsend.pojo.model.Result;
import com.sms.msgsend.pojo.model.ResultGenerator;
import com.sms.msgsend.pojo.domain.SmsSendLogs;
import com.sms.msgsend.service.impl.entityservice.SmsSendLogsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* desc : SmsSendLogsController
* @author wend
* @date 2020/12/08
*/
@RestController
@RequestMapping("/sms/send/logs")
public class SmsLogsController {
    @Resource
    private SmsSendLogsService smsSendLogsService;

    @PostMapping
    public Result add(@RequestBody SmsSendLogs smsSendLogs) {
        smsSendLogsService.save(smsSendLogs);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        smsSendLogsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody SmsSendLogs smsSendLogs) {
        smsSendLogsService.update(smsSendLogs);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        SmsSendLogs smsSendLogs = smsSendLogsService.findById(id);
        return ResultGenerator.genSuccessResult(smsSendLogs);
    }

}
