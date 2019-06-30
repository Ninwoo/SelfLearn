package top.ninwoo.selfLearn.assist;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    /**
     * 基于MD5算法计算摘要
     * @param content
     * @return
     */
    public static String getSHA(String content) {
        MessageDigest messageDigest = null;
        String result = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(content.getBytes());
            result = bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
