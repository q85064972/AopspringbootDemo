package com.sms.msgsend.demo.aliyunMsg;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sms.msgsend.enums.AliyunSmsEnum;
import com.sms.msgsend.enums.SmsParamsEnum_Single;
import com.sms.msgsend.pojo.vo.AliyunPomVo;

import java.util.Map;


/**
 * desc 发送短信的demo
 * @author  wend
 */
public class SendSms {
    /**
     * desc 初始化阿里云短信配置
     */
   /* private static final Map<Enum, String> paramsMap = ReadAliyunSmsConfigPom.getAliyunSmsParams();

    public static void main(String[] args) {
        System.out.println("wendTest:"+paramsMap.toString());
        DefaultProfile profile = DefaultProfile.getProfile(paramsMap.get(AliyunSmsEnum.REGIONID), paramsMap.get(AliyunSmsEnum.ACCESSKEY_ID), paramsMap.get(AliyunSmsEnum.ACCESSKEY_SECRET));
        IAcsClient client = new DefaultAcsClient(profile);
        System.out.println(client == null);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(paramsMap.get(AliyunSmsEnum.SYS_DOMAIN));
        request.setSysVersion(paramsMap.get(AliyunSmsEnum.SYS_VERSION));
        request.setSysAction(paramsMap.get(AliyunSmsEnum.SYS_ACTION));
        request.putQueryParameter("RegionId", paramsMap.get(AliyunSmsEnum.REGIONID));
        request.putQueryParameter("SignName",  paramsMap.get(AliyunSmsEnum.SIGNNAME));
        request.putQueryParameter("PhoneNumbers", "18698151330");
        request.putQueryParameter("TemplateCode", "SMS_149420498");
        request.putQueryParameter("TemplateParam", "{\"code\":\"12345\"}");
        System.out.println(request == null);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response==null);
            System.out.println("wendTest:"+response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }*/

   private static final AliyunPomVo aliyunPomVo = SmsParamsEnum_Single.getInstance().getAliyunPomVo();

    /**
     * desc 第二种实现
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("AliyunPomVo:"+aliyunPomVo.toString());
        DefaultProfile profile = DefaultProfile.getProfile(aliyunPomVo.getRegionId(),aliyunPomVo.getAccessKeyId(),aliyunPomVo.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(aliyunPomVo.getSysDomain());
        request.setSysVersion(aliyunPomVo.getSysVersion());
        request.setSysAction(aliyunPomVo.getSysAction());
        request.putQueryParameter(AliyunSmsEnum.REGIONID.getkey(), aliyunPomVo.getRegionId());
        request.putQueryParameter(AliyunSmsEnum.SIGNNAME.getkey(),  aliyunPomVo.getSignName());
        request.putQueryParameter("PhoneNumbers", "18698151330");
        request.putQueryParameter("TemplateCode", "SMS_149420498");
        request.putQueryParameter("TemplateParam", "{\"code\":\"12345\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("wendTest:"+response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
