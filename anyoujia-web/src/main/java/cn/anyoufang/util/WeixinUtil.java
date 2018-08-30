package cn.anyoufang.util;

import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import cn.anyoufang.utils.RedisUtils;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信公众平台通用接口工具类
 */
public class WeixinUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtil.class);
    private static final String jsapiTicket = "jsapiTicket";
    private static final String accessToken1 = "accessToken1";
    private static final String appid = "wxaa510935818c342a";
    private static final String appSecret = "6b58ad8650e4f2b0e91bd9f039f0c968";


    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
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

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

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
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(ce.getMessage());
            }
        } catch (Exception e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return jsonObject;
    }

    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken() {
        // 获取公众号access_token的链接
        String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = access_token.replace("APPID", appid).replace("APPSECRET", appSecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            // 如果请求成功
            try{
                String accessToken = jsonObject.getString("access_token");
                int expires = jsonObject.getInt("expires_in");
                RedisUtils.setex(accessToken1,accessToken,expires);
                return accessToken;
            }catch (Exception e) {
                if(LOGGER.isInfoEnabled()) {
                    LOGGER.info(e.getMessage() + "," +jsonObject);
                }
            }
        }
        return null;
    }

    /**
     * 获取jsapi_ticket
     *
     * @param “ 凭证appid”
     * @param “appsecret 密钥”
     * @return
     */
    public static String getJsapiTicket(String accessToken) {
        // 获取公众号jsapi_ticket的链接
        String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        if (accessToken != null) {
            String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    String ticket = jsonObject.getString("ticket");
                    int expire = jsonObject.getInt("expires_in");
                    RedisUtils.setex(jsapiTicket,ticket,expire);
                    return ticket;
                } catch (JSONException e) {
                    if(LOGGER.isInfoEnabled()) {
                        LOGGER.info(e.getMessage() + "," +jsonObject);
                    }
                }
            }
        }
        return null;
    }
    public static Map<String, String> sign(String url) {
        Map<String, String> ret = new HashMap();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String at;
        String jsapi_ticket;
        String signature = "";
        if(RedisUtils.get(jsapiTicket)!=null&&RedisUtils.get(accessToken1)!=null) {
            jsapi_ticket = RedisUtils.get(jsapiTicket);
        }else {
            at = getAccessToken();
            jsapi_ticket = getJsapiTicket(at);
        }
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        } catch (UnsupportedEncodingException e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
