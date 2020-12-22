package com.sms.msgsend.service.impl.entityservice;
import com.sms.msgsend.pojo.domain.SmsAccessControl;
import com.sms.msgsend.mapper.core.Service;


/**
* desc : SmsAccessControlController
* @author wend
* @date 2020/12/18
*/
public interface SmsAccessControlService extends Service<SmsAccessControl> {

    /**
     * 通过机构找到对应对象
     * @param organizationCode
     * @return
     */
    SmsAccessControl findByOrganization(String organizationCode);
}
