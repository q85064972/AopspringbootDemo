package com.sms.msgsend.service.impl.sendmsg;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sms.msgsend.constants.SystemConstant;
import com.sms.msgsend.enums.AliyunSmsEnum;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.enums.SmsParamsEnum_Single;
import com.sms.msgsend.pojo.vo.AliyunPomVo;
import com.sms.msgsend.service.SmsSendMsgService;
import com.sms.msgsend.utils.Util;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.sms.msgsend.constants.SendMsgConstant.*;

/**
 * 通过阿里云发送短信
 *
 * @author ${user}
 * @date 2020/12/18
 */
@Service
public class AliyunSendMsgServiceImpl implements SmsSendMsgService {

    private static final Logger logger = LoggerFactory.getLogger(AliyunSendMsgServiceImpl.class);

    private static final AliyunPomVo aliyunPomVo = SmsParamsEnum_Single.getInstance().getAliyunPomVo();

    private static final String PHONE_NUMBERS = "PhoneNumbers";
    private static final String TEMPLATE_CODE = "TemplateCode";
    private static final String TEMPLATE_PARAM = "TemplateParam";

    private static final String SUCCESS = "OK";
    private static final String ALIYUN_MESSAGE = "Message";
    private static final String ALIYUN_CODE = "Code";
    private static final String BIZ_ID = "BizId";


    @SneakyThrows
    @Override
    public JSONObject sendMsg(JSONObject jsonObject) {
        JSONObject contents = jsonObject.getJSONObject(CONTENTS);
        logger.info("AliyunPomVo:" + aliyunPomVo.toString());
        DefaultProfile profile = DefaultProfile.getProfile(aliyunPomVo.getRegionId(), aliyunPomVo.getAccessKeyId(), aliyunPomVo.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(aliyunPomVo.getSysDomain());
        request.setSysVersion(aliyunPomVo.getSysVersion());
        request.setSysAction(aliyunPomVo.getSysAction());
        request.putQueryParameter(AliyunSmsEnum.REGIONID.getkey(), aliyunPomVo.getRegionId());
        request.putQueryParameter(AliyunSmsEnum.SIGNNAME.getkey(), aliyunPomVo.getSignName());
        request.putQueryParameter(PHONE_NUMBERS, contents.getStr(RECVMOBILE));
        request.putQueryParameter(TEMPLATE_CODE, jsonObject.getStr(BUSINESS_TYPE));
        request.putQueryParameter(TEMPLATE_PARAM, contents.getStr(MESSAGE_CONTENT));
        CommonResponse response = client.getCommonResponse(request);
        logger.info("阿里云返回数据:" + response.getData());
        return getResStr(response.getData(),contents);
    }

    /**
     * 获取短信返回结果
     * @param ResData
     * @param contents
     * @return
     */
    private JSONObject getResStr(String ResData,JSONObject contents){
        JSONObject res = Util.getJsonMsg(ResData);

        res.putOnce(RECVMOBILE, contents.getStr(RECVMOBILE));
        res.putOnce(USER_ID, res.getStr(BIZ_ID));
        res.putOnce(MESSAGE_CONTENT, contents.getStr(MESSAGE_CONTENT));

        if (SUCCESS.equals(res.get(ALIYUN_CODE))){
            res.putOnce(RESULT,ResultCodeEnum.SUCCESS.getCode());
            res.putOnce(MESSAGE, ResultCodeEnum.SUCCESS.getvalue());
        }else{
            res.putOnce(RESULT,ResultCodeEnum.FAIL.getCode());
            res.putOnce(MESSAGE, res.get(ALIYUN_CODE)+ SystemConstant.COLON +res.get(ALIYUN_MESSAGE));
        }
        return res;
    }


}
