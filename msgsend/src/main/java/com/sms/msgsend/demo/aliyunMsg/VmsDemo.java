package com.sms.msgsend.demo.aliyunMsg;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyvmsapi.model.v20170525.IvrCallRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.IvrCallRequest.MenuKeyMap;
import com.aliyuncs.dyvmsapi.model.v20170525.IvrCallResponse;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByVoiceRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByVoiceResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 17/6/10.
 * 语音API产品的DEMO程序,工程中包含了一个VmsDemo类，直接通过
 * 执行main函数即可体验语音产品API功能(只需要将AK替换成开通了云通信-语音产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dyvmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 */
public class VmsDemo {

    //产品名称:云通信语音API产品,开发者无需替换
    static final String product = "Dyvmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dyvmsapi.aliyuncs.com";

    //TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "yourAccessKeyId";
    static final String accessKeySecret = "yourAccessKeySecret";

    /**
     * 文本转语音外呼
     * @return
     * @throws ClientException
     */
    public static SingleCallByTtsResponse singleCallByTts() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SingleCallByTtsRequest request = new SingleCallByTtsRequest();
        //必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber("02560000000");
        //必填-被叫号码
        request.setCalledNumber("15000000000");
        //必填-Tts模板ID
        request.setTtsCode("TTS_000000");
        //可选-当模板中存在变量时需要设置此值
        request.setTtsParam("{\"name\":\"test\"}");
        //可选-外部扩展字段,此ID将在回执消息中带回给调用方
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SingleCallByTtsResponse singleCallByTtsResponse = acsClient.getAcsResponse(request);

        return singleCallByTtsResponse;

    }

    /**
     * 语音文件外呼
     * @return
     * @throws ClientException
     */
    public static SingleCallByVoiceResponse singleCallByVoice() throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SingleCallByVoiceRequest request = new SingleCallByVoiceRequest();
        //必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber("025000000");
        //必填-被叫号码
        request.setCalledNumber("15000000000");
        //必填-语音文件ID
        request.setVoiceCode("3a7c382b-ee87-493f-bfa0-b9fd6f31f8bb.wav");
        //可选-外部扩展字段
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SingleCallByVoiceResponse singleCallByVoiceResponse = acsClient.getAcsResponse(request);

        return singleCallByVoiceResponse;
    }

    /**
     * 交互式语音应答
     * @return
     * @throws ClientException
     */
    public static IvrCallResponse ivrCall() throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        IvrCallRequest request = new IvrCallRequest();
        //必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber("057156210000");
        //必填-被叫号码
        request.setCalledNumber("15000000000");
        request.setPlayTimes(3L);

        //必填-语音文件ID或者tts模板的模板号,有参数的模板需要设置模板变量的值
        //request.setStartCode("ebe3a2b5-c287-42a4-8299-fc40ae79a89f.wav");
        request.setStartCode("TTS_713900000");
        request.setStartTtsParams("{\"product\":\"aliyun\",\"code\":\"123\"}");
        List<MenuKeyMap> menuKeyMaps = new ArrayList<MenuKeyMap>();
        MenuKeyMap menuKeyMap1 = new MenuKeyMap();
        menuKeyMap1.setKey("1");
        menuKeyMap1.setCode("9a9d7222-670f-40b0-a3af.wav");
        menuKeyMaps.add(menuKeyMap1);
        MenuKeyMap menuKeyMap2 = new MenuKeyMap();
        menuKeyMap2.setKey("2");
        menuKeyMap2.setCode("44e3e577-3d3a-418f-932c.wav");
        menuKeyMaps.add(menuKeyMap2);
        MenuKeyMap menuKeyMap3 = new MenuKeyMap();
        menuKeyMap3.setKey("3");
        menuKeyMap3.setCode("TTS_71390000");
        menuKeyMap3.setTtsParams("{\"product\":\"aliyun\",\"code\":\"123\"}");
        menuKeyMaps.add(menuKeyMap3);
        request.setMenuKeyMaps(menuKeyMaps);
        //结束语可以使一个无参模板或者一个语音文件ID
        request.setByeCode("TTS_71400007");

        //可选-外部扩展字段
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        IvrCallResponse ivrCallResponse = acsClient.getAcsResponse(request);

        return ivrCallResponse;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {
        SingleCallByTtsResponse singleCallByTtsResponse = singleCallByTts();
        System.out.println("文本转语音外呼---------------");
        System.out.println("RequestId=" + singleCallByTtsResponse.getRequestId());
        System.out.println("Code=" + singleCallByTtsResponse.getCode());
        System.out.println("Message=" + singleCallByTtsResponse.getMessage());
        System.out.println("CallId=" + singleCallByTtsResponse.getCallId());

        Thread.sleep(20000L);

        SingleCallByVoiceResponse singleCallByVoiceResponse = singleCallByVoice();
        System.out.println("语音文件外呼---------------");
        System.out.println("RequestId=" + singleCallByVoiceResponse.getRequestId());
        System.out.println("Code=" + singleCallByVoiceResponse.getCode());
        System.out.println("Message=" + singleCallByVoiceResponse.getMessage());
        System.out.println("CallId=" + singleCallByVoiceResponse.getCallId());

        Thread.sleep(20000L);

        IvrCallResponse ivrCallResponse = ivrCall();
        System.out.println("交互式语音应答---------------");
        System.out.println("RequestId=" + ivrCallResponse.getRequestId());
        System.out.println("Code=" + ivrCallResponse.getCode());
        System.out.println("Message=" + ivrCallResponse.getMessage());
        System.out.println("CallId=" + ivrCallResponse.getCallId());
    }

}
