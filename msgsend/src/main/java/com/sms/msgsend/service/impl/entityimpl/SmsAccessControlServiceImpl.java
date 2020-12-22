package com.sms.msgsend.service.impl.entityimpl;

import com.sms.msgsend.mapper.impl.SmsAccessControlMapper;
import com.sms.msgsend.pojo.domain.SmsAccessControl;
import com.sms.msgsend.service.impl.entityservice.SmsAccessControlService;
import com.sms.msgsend.mapper.core.AbstractService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


/**
* desc : SmsAccessControlServiceImpl
* @author wend
* @date 2020/12/18
*/
@Service
public class SmsAccessControlServiceImpl extends AbstractService<SmsAccessControl> implements SmsAccessControlService {
    @Resource
    private SmsAccessControlMapper smsAccessControlMapper;

    @Override
    public SmsAccessControl findByOrganization(String organizationCode) {
        return smsAccessControlMapper.findByOrganization(organizationCode);
    }
}
