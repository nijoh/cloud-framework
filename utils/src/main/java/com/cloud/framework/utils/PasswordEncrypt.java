package com.cloud.framework.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具
 * */
public class PasswordEncrypt {
    public static String encryptSHA256(String str){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte byteBuffer[] = messageDigest.digest();
            StringBuffer strHexString = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            // 得到返回結果
            String strResult = strHexString.toString();
            return strResult;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("明文加密失败");
        }
    }
}
