package cn.anyoufang.util;

import cn.anyoufang.enums.WxConstant;
import cn.anyoufang.utils.RedisUtils;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 微信公众平台通用接口工具类
 * @author daiping
 */
public class WeixinUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtil.class);
    public static final String GET = "GET";


    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if (GET.equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(ce.getMessage());
            }
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return jsonObject;
    }

    /**
     * 获取access_token
     * 增加双重检查机制，避免多线程问题
     */
    public static String getAccessToken() {
        // 获取公众号access_token的链接
        String at = WxConstant.ACCESS_TOKEN.getValue();
        String appId = WxConstant.APP_ID.getValue();
        String appSecret = WxConstant.APP_SECRET.getValue();
        if(RedisUtils.get(at) == null) {
            synchronized (RedisUtils.class){
                if(RedisUtils.get(at) == null){
                    String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
                    String requestUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
                    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
                    if (null != jsonObject) {
                        // 如果请求成功
                        try {
                            String accessToken = jsonObject.getString("access_token");
                            int expires = jsonObject.getInt("expires_in");
                            RedisUtils.setex(at, accessToken, expires);
                            return accessToken;
                        } catch (Exception e) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info(e.getMessage() + "," + jsonObject);
                            }
                        }
                    }
                }
                return RedisUtils.get(at);
            }
        }
        return RedisUtils.get(at);
    }

    /**
     * 获取jsapi_ticket
     *
     * @param “          凭证appid”
     * @param “appsecret 密钥”
     */
    public static String getJsapiTicket(String accessToken) {
        // 获取公众号jsapi_ticket的链接
        String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        if (accessToken != null) {
            String requestUrl = jsapiTicketUrl.replace("ACCESS_TOKEN", accessToken);
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    String ticket = jsonObject.getString("ticket");
                    int expire = jsonObject.getInt("expires_in");
                    String jsApiTicket = WxConstant.JS_API_TICKET.getValue();
                    RedisUtils.setex(jsApiTicket, ticket, expire);
                    return ticket;
                } catch (JSONException e) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info(e.getMessage() + "," + jsonObject);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取jsapi 所需基本信息
     */
    public static Map<String, String> sign(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> ret = new HashMap(3);
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();


        String jsapiTicket;
        if (RedisUtils.get(WxConstant.JS_API_TICKET.getValue()) != null && RedisUtils.get(WxConstant.ACCESS_TOKEN.getValue()) != null) {
            jsapiTicket = RedisUtils.get(WxConstant.JS_API_TICKET.getValue());
        } else {
            String at = getAccessToken();
            jsapiTicket = getJsapiTicket(at);
        }
        //注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    /**
     *
     * @param hash
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     *
     * @return
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    /**
     *
     * @return
     */
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}
