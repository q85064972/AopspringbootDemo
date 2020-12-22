package com.sms.msgsend.enums;

import com.sms.msgsend.pojo.vo.AliyunPomVo;
import com.sms.msgsend.pojo.vo.TestPomVo;

/**
 * desc Enum单例模式实现文件配置
 * @author ${user}
 * @date 2020/12/10
 */
public enum SmsParamsEnum_Single {
    /**
     *
     */
    INSTANCE;

    private final AliyunPomVo aliyunPomVo;
    private final TestPomVo testPomVo;

    SmsParamsEnum_Single()
    {
        System.out.println("INSTANCE 时进入这个方法！");
        // 初始化阿里云配置
        aliyunPomVo = new AliyunPomVo();
        aliyunPomVo.setSignName(AliyunSmsEnum.SIGNNAME.getvalue());
        aliyunPomVo.setAccessKeyId(AliyunSmsEnum.ACCESSKEY_ID.getvalue());
        aliyunPomVo.setAccessKeySecret(AliyunSmsEnum.ACCESSKEY_SECRET.getvalue());
        aliyunPomVo.setRegionId(AliyunSmsEnum.REGIONID.getvalue());
        aliyunPomVo.setSysAction(AliyunSmsEnum.SYS_ACTION.getvalue());
        aliyunPomVo.setSysDomain(AliyunSmsEnum.SYS_DOMAIN.getvalue());
        aliyunPomVo.setSysVersion(AliyunSmsEnum.SYS_VERSION.getvalue());
        // 初始化test配置
        testPomVo = new TestPomVo();
        testPomVo.setTest(TestSmsEnum.TEST.getvalue());
    }

    /**
     * 实例化
     * @return
     */
    public static SmsParamsEnum_Single getInstance()
    {
        return INSTANCE;
    }

    /**
     * 配置文件加载
     * @return
     */
    public  AliyunPomVo getAliyunPomVo()
    {
        return aliyunPomVo;
    }

    public TestPomVo getTestPomVo(){
        return testPomVo;
    }
}
