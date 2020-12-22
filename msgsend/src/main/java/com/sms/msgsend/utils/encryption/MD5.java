package com.sms.msgsend.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:MD5Util
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   Administrator
 * @version
 * @since    Ver 1.1
 * @Date	 2011-1-21		下午12:36:33
 *
 * @see
 */
@Deprecated(since = "2020-12-20 Be replace hutool", forRemoval = true)
public class MD5 {

    public static String md5(String originalStr) {
        if ("".equals(originalStr) && originalStr == null) {
            return "";
        }
        String digestStr = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException exc) {
            System.err.println("This version of Java does not support MD5.");
            return null;
        }
        md.reset();
        md.update(originalStr.getBytes());
        byte[] rawDigest = md.digest();

        StringBuffer str = new StringBuffer();
        for (int i = 0; i < rawDigest.length; i++) {
            String bStr = Integer.toHexString((int) rawDigest[i] & 0xff);
            if (bStr.length() == 1) {
                str.append("0" + bStr);
            } else {
                str.append(bStr);
            }
        }
        digestStr = str.toString();

        return digestStr;
    }
}

