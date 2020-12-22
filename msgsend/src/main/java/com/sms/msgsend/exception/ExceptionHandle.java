package com.sms.msgsend.exception;

import com.aliyuncs.exceptions.ClientException;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.pojo.model.Result;
import com.sms.msgsend.pojo.model.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * desc：
 * 全局错误处理
 * @author  wend
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        logger.error(" 错误信息:"+e.getMessage());
        e.printStackTrace();
        // 权限异常
        if (e instanceof AccessControlException) {
            return ResultGenerator.genFailResult(ResultCodeEnum.AccessControlError);
        }
        //加密/解密异常
        if (e instanceof EncryptionException){
            return ResultGenerator.genFailResult(ResultCodeEnum.EncryptionError);
        }
        if (e instanceof  DecryptionException){
            return ResultGenerator.genFailResult(ResultCodeEnum.DecryptionError);
        }
        // 阿里云访问异常
        if (e instanceof ServiceException){
            return ResultGenerator.genFailResult(ResultCodeEnum.AliyunServerError);
        }
        if (e instanceof ClientException){
            return ResultGenerator.genFailResult(ResultCodeEnum.AliyunClientError);
        }
        logger.error("【系统异常】{}", e);
        return ResultGenerator.genFailResult(ResultCodeEnum.FAIL);
    }
}
