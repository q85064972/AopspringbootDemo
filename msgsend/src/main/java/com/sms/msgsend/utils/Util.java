package com.sms.msgsend.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.sms.msgsend.constants.SystemConstant;
import com.sms.msgsend.enums.ResultCodeEnum;
import com.sms.msgsend.exception.DecryptionException;
import com.sms.msgsend.exception.EncryptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sms.msgsend.enums.EncryptionMethodEnum.*;


/**
 * desc 工具类
 * 封装 hutool 没有的工具类
 *
 * @author ${user}
 * @date 2020/12/14
 */
public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    /**
     * desc 简单判断是否是XML类型
     *
     * @param str
     * @return
     */
    public static boolean isXMLType(String str) {
        boolean result = false;
        if (StrUtil.isNotBlank(str)) {
            str = str.trim();
            if (XML.LT.equals(str.charAt(SystemConstant.ZERO)) && XML.GT.equals(str.charAt(str.length() - SystemConstant.ONE))) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 解密方法
     *
     * @param contents
     * @return
     */
    public static String decrypt(String encryptionMethod, String encryptionKey, String password, String contents) {
        String decryptStr = "";
        try {
            // 解密
            if (AESMethod.getCode().equals(encryptionMethod)) {
                //构建
                AES aes = SecureUtil.aes(encryptionKey.getBytes());
                decryptStr = aes.decryptStr(contents);

            }
            if (DESMethod.getCode().equals(encryptionMethod)) {
                DES des = SecureUtil.des(encryptionKey.getBytes());
                decryptStr = des.decryptStr(contents);
            }
            if (MD5Method.getCode().equals(encryptionMethod)) {
                decryptStr = SecureUtil.md5(contents);
                if (password.equals(decryptStr) == false) {
                    logger.error("MD5验签校验失败");
                    throw new DecryptionException(ResultCodeEnum.DecryptionError.getvalue());
                }
                decryptStr = contents;
            }
            if (StrUtil.isBlank(decryptStr)) {
                logger.error("解密校验失败");
                throw new DecryptionException(ResultCodeEnum.DecryptionError.getvalue());
            }
            System.out.println("decryptStr:" + decryptStr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DecryptionException(ResultCodeEnum.DecryptionError.getvalue());
        }

        return decryptStr;
    }

    /**
     * 加密方法
     *
     * @param contents
     * @return
     */
    public static String encrypt(String encryptionMethod, String encryptionKey, String contents) {
        String encryptStr = "";
        try {
            // 解密
            if (AESMethod.getCode().equals(encryptionMethod)) {
                //构建
                AES aes = SecureUtil.aes(encryptionKey.getBytes());
                encryptStr = aes.encryptHex(contents);
            }
            if (DESMethod.getCode().equals(encryptionMethod)) {
                DES des = SecureUtil.des(encryptionKey.getBytes());
                encryptStr = des.encryptHex(contents);
            }
            if (MD5Method.getCode().equals(encryptionMethod)) {
                encryptStr = SecureUtil.md5(contents);
            }
            if (StrUtil.isBlank(encryptStr)) {
                logger.error("加密校验失败");
                throw new EncryptionException(ResultCodeEnum.EncryptionError.getvalue());
            }
            System.out.println("encryptStr:" + encryptStr);
        } catch (Exception e) {
            throw new EncryptionException(ResultCodeEnum.EncryptionError.getvalue());
        }

        return encryptStr;
    }

    /**
     * 得到json内容
     *
     * @param shortMessageVO
     * @return
     */
    public static JSONObject getJsonMsg(String shortMessageVO) {
        JSONObject jsonObject = null;
        // 判断是否为XML
        if (Util.isXMLType(shortMessageVO)) {
            // XML转为JSON处理
            jsonObject = XML.toJSONObject(shortMessageVO);
        }

        // 判断其是否为JSON
        if (JSONUtil.isJson(shortMessageVO)) {
            jsonObject = JSONUtil.parseObj(shortMessageVO);
        }
        // todo 接收其他方式报文体

        return jsonObject;
    }


}
