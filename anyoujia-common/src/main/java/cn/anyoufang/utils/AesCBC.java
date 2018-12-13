package cn.anyoufang.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理
 * 对原始数据进行AES加密后，在进行Base64编码转化；
 * @author daping
 */
public class AesCBC {
    /**
     * 已确认加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey="u798y49H87BUii7g";
    private static String ivParameter="28v6njy9ONIY9OBU";
    private static String encodingFormat="utf-8";
    private static AesCBC instance=null;
    private static final String salt = "575gh5rr556Dfhr67Ohrt8";



    private AesCBC(){

    }
    public static AesCBC getInstance(){
        if (instance==null){
            instance= new AesCBC();
        }
        return instance;
    }

    /**
     * 加密
     * @param sSrc
     * @return
     * @throws Exception
     */
    public String encrypt(String sSrc) throws Exception {

        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("utf-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        //此处使用BASE64做转码。
        String result = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted).replaceAll("\r|\n", "");;
        return result;
    }

    /**
     * 解密
     * @param sSrc
     * @return
     */
    public String decrypt(String sSrc){
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 =org.apache.commons.codec.binary.Base64.decodeBase64(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
}