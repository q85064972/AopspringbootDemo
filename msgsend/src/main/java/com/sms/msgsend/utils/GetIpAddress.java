package com.sms.msgsend.utils;

import cn.hutool.json.XML;
import com.sms.msgsend.constants.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * desc 获取IP的方法
 *
 * @author ${wend}
 * @date 2020/12/14
 */
public class GetIpAddress {

    public static final String UNKNOWN = "unknown";
    public static final String LOCAL_IP4 = "127.0.0.1";
    public static final String LOCAL_IP6 = "0:0:0:0:0:0:0:1";


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * @param request
     * @return
     */
    public static String get(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals(LOCAL_IP4) || ipAddress.equals(LOCAL_IP6)){
                //根据网卡取本机配置的IP
                InetAddress inetAddress=null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                    ipAddress= inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()> SystemConstant.FIFTEEN){
            if(ipAddress.indexOf(SystemConstant.SPOT)>SystemConstant.ZERO){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(SystemConstant.COMMA));
            }
        }
        return ipAddress;
    }
}
