package com.bigdata.coin.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;

/**
 * EncryptUtil.
 */
public class EncryptUtil {

    /**
     * sourceToSha1.
     */
    public static String sourceToSha1(String source) {
        return byte2String(getSha1(source.getBytes()));
    }

    /**
     * getSha1.
     */
    private static byte[] getSha1(byte[] source) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.update(source);
            return sha1.digest();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * getMD5.
     */
    public static String getMd5(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(source.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * sourceToAes.
     */
    public static String sourceToAes(String source) {
        byte[] base64decodedBytes = Base64.getDecoder().decode(source);
        String result = null;
        try {
            result = new String(base64decodedBytes, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    /**
     * aesToSource.
     */
    public static String aesToSource(String aes) {
        try {
            String plainText = Base64.getEncoder().encodeToString(aes.getBytes("utf-8"));
            return plainText;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * getKey.
     */
    private static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "q360bigdata";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * byte2String.
     */
    private static String byte2String(byte[] bytes) {
        int len = bytes.length * 2;
        StringBuffer stringBuffer = new StringBuffer(len);
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hex);
        }
        return stringBuffer.toString();
    }

    /**
     * test.
     */
    public static void main(String[] args) {
        System.out.println(sourceToAes("123456"));
        System.out.println(aesToSource("123456"));
        System.out.println(sourceToSha1("123456"));
        System.out.println(getMd5("qiaoqiuhuaqiaoqiuhua"));
    }


}
