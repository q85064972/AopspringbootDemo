package com.sms.msgsend.service.impl.entityimpl;

import com.sms.msgsend.mapper.core.AbstractService;
import com.sms.msgsend.mapper.impl.SmsSendLogsMapper;
import com.sms.msgsend.pojo.domain.SmsSendLogs;
import com.sms.msgsend.service.impl.entityservice.SmsSendLogsService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


/**
* desc : SmsSendLogsServiceImpl
* @author wend
* @date 2020/12/08
*/
@Service
public class SmsSendLogsServiceImpl extends AbstractService<SmsSendLogs> implements SmsSendLogsService {
    @Resource
    private SmsSendLogsMapper smsSendLogsMapper;

}
