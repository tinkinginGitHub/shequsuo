package cn.anyoufang.utils;


import cn.anyoufang.entity.Data;
import cn.anyoufang.entity.InitParam;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

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
    // 加密
    public String encrypt(String sSrc) throws Exception {

        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("utf-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        //此处使用BASE64做转码。
        String result = new BASE64Encoder().encode(encrypted).replaceAll("\r|\n", "");;
        return result;
    }

    // 解密
    public String decrypt(String sSrc) throws Exception {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,encodingFormat);
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
    public static  Map<String,Object> updateLogin(String username, String session) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("session",session);
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("updatelogin").append(json).append(salt).toString();
        System.out.println(result);
        String sign = Md5Utils.md5(result,"utf-8");
        System.out.println(sign);
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("updatelogin");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",username);
        data.put("session",session);
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        System.out.println(ss);
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        System.out.println(response);
      //  Map<String,Object> result1 = parseResponse(response);
        return null;
    }

    public static Map<String,Object> doLogin(String account, String password, String ip) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",account);
        map.put("password",password);
        map.put("ip",ip);
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("login").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("login");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",account);
        data.put("password",password);
        data.put("ip",ip);
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        System.out.println(response);
        return null;
    }
    public static Map<String,Object> doRegister(String account, String password) throws Exception {
        StringBuilder sb = new StringBuilder();
        Data data1 = new Data();
        Map<String,String> map = new HashMap<>();
        map.put("username",account);
        map.put("password",password);
        map.put("type","1");
        data1.setData(map);
        String json = JsonUtils.objectToJson(data1.getData());
        System.out.println(json);
        String result =  sb.append("Sso").append("register").append(json).append(salt).toString().replaceAll("\t|\n|\r","");
        System.out.println(result);
        String sign = Md5Utils.md5(result,"utf-8");
        InitParam p = new InitParam();
        p.setMod("Sso");
        p.setFun("register");
        p.setSign(sign);
        Map<String,String> data = new HashMap<>();
        data.put("username",account);
        data.put("password",password);
        data.put("type","1");
        p.setData(data);
        String ss =  JsonUtils.objectToJson(p);
        // 加密
        String enString = AesCBC.getInstance().encrypt(ss).replaceAll("\r|\n", "").trim().replaceAll("\\+","%2B");
        String param = "sp=" + enString;
        String response =  SimulateGetAndPostUtil.sendPost("http://144.anyoujia.com/Sso/Api/index/",param);
        System.out.println(response);
        return null;
    }
    public static void main(String[] args) throws Exception {
        //updateLogin("daping0","KF9taj6UCYzeVVpfV/IXYdvBEcQDNhTRLFmEiMhuS7gx30RB0Ba7q1dvOPMRlpy1");
        //doLogin("daping0","4607e782c4d86fd5364d7e4508bb10d9","125.19.1.32");
        doRegister("JIANMING","4607e782c4d86fd5364d7e4508bb10d9");
        //String s = Md5Utils.md5("Ssoupdatelogin{\"session\":\"/q5I3d1Z4FyF1Mm0cpN3obPwyyC4F9jeKQ/aRAyKLu/ieTxONNC+T1vQ5PbMXXoh\",\"username\":\"sunpeng\"}575gh5rr556Dfhr67Ohrt8","utf-8");
        //System.out.println(s);
    }

}