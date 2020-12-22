package com.sms.msgsend.constants;

import com.sms.msgsend.enums.AliyunSmsEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * desc:读取基本配置
 * 单例模式实现  饿汉式
 * @author ${wend}
 * @date 2020/12/10
 */
@Deprecated
public class ReadAliyunSmsConfigPom implements Serializable {

    private ReadAliyunSmsConfigPom() {
          if (AliyunSmsParams!=null){
              throw new RuntimeException("单例构造器禁止反射调用");
          }
    }

    private static final Map<Enum, String> AliyunSmsParams = readAliyunSmsPom();

    /**
     * 读取阿里云短信配置
     * @return
     */
    private static final Map<Enum, String> readAliyunSmsPom() {
        try {
            Map<Enum, String> aliyunSmsCon = new HashMap<Enum, String>(10);
            aliyunSmsCon.put(AliyunSmsEnum.SYS_DOMAIN,AliyunSmsEnum.SYS_DOMAIN.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.SYS_ACTION,AliyunSmsEnum.SYS_ACTION.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.SYS_VERSION,AliyunSmsEnum.SYS_VERSION.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.REGIONID,AliyunSmsEnum.REGIONID.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.SIGNNAME, AliyunSmsEnum.SIGNNAME.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.ACCESSKEY_ID,AliyunSmsEnum.ACCESSKEY_ID.getvalue());
            aliyunSmsCon.put(AliyunSmsEnum.ACCESSKEY_SECRET,AliyunSmsEnum.ACCESSKEY_SECRET.getvalue());
            return aliyunSmsCon;
        } catch (Exception e) {
            throw new RuntimeException("读取阿里云短信配置文件失败");

        }
    }

    /**
     *desc : 对外静态方法
     * @return
     */
    public static Map<Enum,String> getAliyunSmsParams(){
        return AliyunSmsParams;
    }

    /**
     * 一旦单例模式涉及到序列化和反序列化,一定要小心单例被破坏掉
     * 这个方法是反射调用的,如果不写这个方法,序列化就会破坏单例
     */
    private Object readResolve(){
        return AliyunSmsParams;
    }

}
