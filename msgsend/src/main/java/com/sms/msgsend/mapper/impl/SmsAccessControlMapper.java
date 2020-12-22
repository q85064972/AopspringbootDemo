package com.sms.msgsend.mapper.impl;

import com.sms.msgsend.mapper.core.Mapper;
import com.sms.msgsend.pojo.domain.SmsAccessControl;
import org.apache.ibatis.annotations.Select;



/**
 * desc 权限控制Mapper
 * @author wend
 */
public interface SmsAccessControlMapper extends Mapper<SmsAccessControl> {

    /**
     * desc 通过机构查到对象
     * @param organizationCode
     * @return
     */
    @Select({"<script>",
            "SELECT",
            "organization_code,\n" +
            "id,\n" +
            "organization_name,\n" +
            "password,\n" +
            "encryption_method,\n" +
            "send_method,\n" +
            "ip,\n" +
            "create_by,\n" +
            "update_by,\n" +
            "create_date,\n" +
            "update_date,\n" +
            "is_validity,\n" +
            "is_delete",
            "FROM",
            "sms_access_control",
            " <where> 1=1 ",
            "AND organization_code= #{organizationCode}",
            "AND is_validity = 1 ",
            "AND is_delete = 0 ",
            "</where>",
            "</script>"})
    SmsAccessControl findByOrganization(String organizationCode);


}
