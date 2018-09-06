package cn.anyoufang.util;


import cn.anyoufang.message.aes.AesException;
import cn.anyoufang.message.aes.WXBizMsgCrypt;

public class AesUtils {

    private static final String APP_ID = "wxaa510935818c342a";
    private static final String TOKEN = "wxxwhome";
    private static final String ENCODING_AES_KEY ="yourkey";

    /*
     * 解密
     */

    public static String descMessage(String encrypt, String msgSignature,
                                     String timestamp, String nonce) throws AesException {
        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(TOKEN, ENCODING_AES_KEY, APP_ID);
        String result = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
        return result;
    }


    /*加密*/
    public static String aescMessage(String replyMsg, String timestamp,
                                     String nonce) throws AesException {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(TOKEN, ENCODING_AES_KEY, APP_ID);
        String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
        return mingwen;
    }
}

