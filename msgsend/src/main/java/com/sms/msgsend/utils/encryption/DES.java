package com.sms.msgsend.utils.encryption;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

@Deprecated(since = "2020-12-20 Be replace hutool", forRemoval = true)
public class DES {


    private static String ENCRYPT = "encrypt";
    private static String DECRYPT = "decrypt";

    /**
     * 解密数据
     * @param message
     * @return
     * @throws Exception
     */
    public static String decrypt(String message,String sKey) throws Exception {
        byte[] bytesrc = convertHexString(message);
        Cipher cipher = getInstance(message,sKey);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte,"UTF-8");
    }

    /**
     * 加密数据
     * @param message
     * @return
     * @throws Exception
     */
    public static String encrypt(String message,String sKey) throws Exception {
        StringBuffer hexString = new StringBuffer();
        Cipher cipher = getInstance(ENCRYPT,sKey);
        byte[] bytes = cipher.doFinal(message.getBytes("UTF-8"));
        for (int i = 0; i < bytes.length; i++) {
            String plainText = Integer.toHexString(0xff & bytes[i]);
            if (plainText.length() < 2){
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }
        return hexString.toString().toUpperCase();
    }

    private static Cipher  getInstance(String method,String sKey) throws Exception{
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(sKey.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(sKey.getBytes("UTF-8"));
        if (ENCRYPT.equals(method)){
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        }
        if (DECRYPT.equals(method)){
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        }
        return cipher;
    }

    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

}
