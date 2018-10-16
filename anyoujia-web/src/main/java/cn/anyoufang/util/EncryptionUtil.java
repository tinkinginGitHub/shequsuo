package cn.anyoufang.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加解密工具类
 */
public class EncryptionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);


    /**
     * 使用参数中的密钥加密
     * @param "明文"
     * @param "密钥"
     * @return 密文
     */
    public static String encrypt(String sSrc, String sKey) {
        try{
            if (sKey == null) {
                return null;
            }

            byte[] raw = sKey.getBytes("utf-8");
            byte[] iv = "28v6njy9ONIY9OBU".getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv));
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            //此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return new Base64().encodeToString(encrypted);
        }catch(Exception e){
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return null;
        }
    }

    /**
     * 使用参数中的密钥解密
     * @param "密文"
     * @param "密钥"
     * @return 明文
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            if (sKey == null) {
                return null;
            }
            if (sKey.length() != 128) {
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            byte[] encrypted1 = new Base64().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                if(LOGGER.isInfoEnabled()) {
                    LOGGER.info(e.getMessage());
                }
                return null;
            }
        } catch (Exception ex) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(ex.getMessage());
            }
            return null;
        }
    }

}
