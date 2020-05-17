package com.lxt.seatorder.utils;


import android.util.Base64;
import android.util.Log;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;

import static com.lxt.seatorder.utils.AppConfig.TAG;

/**
 * Created by Lxt Jxfen on 2019-12-10.
 * email: 1771874056@qq.com
 */
public class EncryptUtil {

    public static final String CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * 得到公钥
     *
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(publicKey, Base64.NO_WRAP));
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return rsaPublicKey;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param pKey
     * @return
     */
    public static String publicEncrypt(String data, String pKey) throws Exception {
        RSAPublicKey publicKey = getPublicKey(pKey);
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");    //注意这句话，前端加密采用的PKCS1..!!!!!
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(data.getBytes());
        String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
        Log.i(TAG, "publicEncrypt: " + s);
        return s;
    }

    /**
     * 获取指定长度字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
