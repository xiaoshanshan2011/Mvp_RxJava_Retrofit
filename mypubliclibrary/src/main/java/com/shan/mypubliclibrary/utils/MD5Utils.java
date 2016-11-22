package com.shan.mypubliclibrary.utils;

import java.security.MessageDigest;

/**
 * MD5加解密（消息摘要算法第五版）
 * Created by chenjunshan on 2016/7/29.
 */

public class MD5Utils {

    /**
     * MD5加密，32位
     *
     * @param en
     * @return
     */
    public static String encryption(String en) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = en.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte) charArray[i];

            byte[] md5Bytes = md5.digest(byteArray);

            StringBuffer hexValue = new StringBuffer();

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
