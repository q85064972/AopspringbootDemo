package com.sms.msgsend.demo.aliyunMsg;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sms.msgsend.enums.SmsParamsEnum_Single;
import com.sms.msgsend.pojo.vo.AliyunPomVo;


public class QuerySmsTemplate {

    private static final AliyunPomVo aliyunPomVo = SmsParamsEnum_Single.getInstance().getAliyunPomVo();
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile(aliyunPomVo.getRegionId(),aliyunPomVo.getAccessKeyId(),aliyunPomVo.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySmsSign");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "中科微服务");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
