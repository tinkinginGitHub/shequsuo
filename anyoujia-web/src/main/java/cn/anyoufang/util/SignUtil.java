package cn.anyoufang.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 检查签名
 * @author daiping
 */
public class SignUtil {

    /**
     * 与开发模式接口配置信息中的Token保持一致
     */
    private static final String TOKEN = "smart";

    /**
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return 成功返回true ，否则返回false
     */
    public static boolean checkSignature(String signature, String timestamp,
                                         String nonce) {
        String[] arr = new String[] { TOKEN, timestamp, nonce };
        // 将token，timestamp，nonce三个参数字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = btyeToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;

    }

    private static String btyeToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字符转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tmpArr = new char[2];
        tmpArr[0] = digit[(mByte >>> 4) & 0x0F];
        tmpArr[1] = digit[mByte & 0x0F];

        String s = new String(tmpArr);
        return s;
    }

}

