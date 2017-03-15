package com.remobile.des;

import android.widget.Toast;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;


public class RCTDes extends ReactContextBaseJavaModule {
    private final static String DES = "DES";
    private final static String DES_CBC = "DES/CBC/PKCS5Padding";

    public RCTDes(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RCTDes";
    }

    @ReactMethod
    public void encrypt(String data, String key, Callback success, Callback error) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes(), null);
            String strs = new BASE64Encoder().encode(bt);
            success.invoke(strs);
        } catch (Exception e) {
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void decrypt(String data, String key, Callback success, Callback error) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf,key.getBytes(),null);
            String strs = new String(bt);
            success.invoke(strs);
        } catch (Exception e) {
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void encryptCbc(String data, String key, String vec, Callback success, Callback error) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes(), vec);
            String strs = new BASE64Encoder().encode(bt);
            success.invoke(strs);
        } catch (Exception e) {
            error.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void decryptCbc(String data, String key, String vec, Callback success, Callback error) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf,key.getBytes(),vec);
            String strs = new String(bt);
            success.invoke(strs);
        } catch (Exception e) {
            error.invoke(e.getMessage());
        }
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @param vecStr  CBC加密向量String字符串
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key, String vecStr) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // CBC 加密向量(只有CBC加密时用到)
        IvParameterSpec iv;

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher;

        if (vecStr != null) {
            iv = new IvParameterSpec(vecStr.getBytes());
            cipher = Cipher.getInstance(DES_CBC);
            // 用密钥和加密向量初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
        } else {
            cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        }

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @param vecStr  CBC加密向量String字符串
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key, String vecStr) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // CBC 加密向量(只有CBC解密时用到)
        IvParameterSpec iv;

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher;

        if (vecStr != null) {
            iv = new IvParameterSpec(vecStr.getBytes());
            cipher = Cipher.getInstance(DES_CBC);
            // 用密钥和加密向量初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        } else {
            cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        }


        return cipher.doFinal(data);
    }
}
