package cn.anyoufang.service.Impl;

import cn.anyoufang.entity.*;
import cn.anyoufang.entity.selfdefined.ResultWx;
import cn.anyoufang.mapper.SpMemberWxMapper;
import cn.anyoufang.service.WxUserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    private static final Logger log = LoggerFactory.getLogger(WxUserServiceImpl.class);
    @Autowired
    private SpMemberWxMapper wxMapper;



    @Override
    public ResultWx CheckUserwXInfoBand(String code)
            throws IOException {
        if (code != null) {
            //获取openid和session_key
            String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=wxd6c32604de641660&secret=6122f996b1e99597cae0c7bb03fc43b2&js_code=CODE&grant_type=authorization_code";

            //获取返回的code
            String requestUrl = getOpenIdUrl.replace("CODE", code);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //向微信发送请求并获取response
            String response = httpClient.execute(httpGet, responseHandler);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(response);
            if(jsonObject.get("session_key") == null || jsonObject.get("openid") == null) {
                return null;
            }
            String openId = jsonObject.get("openid").getAsString();

           List<ResultWx> list =  wxMapper.selectByOpenid(openId);
           if(list.size() == 0) {
               return null;
           }else {
            return list.get(0);
           }
        }
        return null;
    }

    @Override
    public String getOpenId(String code) throws IOException {
        if (code != null) {
            String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=wxd6c32604de641660&secret=6122f996b1e99597cae0c7bb03fc43b2&js_code=CODE&grant_type=authorization_code";
            //获取返回的code
            String requestUrl = getOpenIdUrl.replace("CODE", code);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //向微信发送请求并获取response
            String response = httpClient.execute(httpGet, responseHandler);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(response);
            if(jsonObject.get("session_key") == null || jsonObject.get("openid") == null) {
                return null;
            }
            String openId = jsonObject.get("openid").getAsString();
            return openId;
        }
        return null;
    }

    /**
     * 此方法原为保存微信公众号用户信息使用，先暂存
     * @param accessToken
     * @param openId
     * @throws Exception
     */
    @Override
    public void saveWxUserBasicInfo(String accessToken, String openId) throws Exception {

        //获取用户基本信息的连接
        String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String userInfoUrl = getUserInfo
                .replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openId);
        HttpGet httpGetUserInfo = new HttpGet(userInfoUrl);
        HttpClient httpClient = HttpClientBuilder.create().build();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String userInfo = httpClient.execute(httpGetUserInfo,
                responseHandler);
        //微信那边采用的编码方式为ISO8859-1所以需要转化
        String json = new String(userInfo.getBytes("ISO-8859-1"), "UTF-8");

        JsonParser parser = new JsonParser();
        JsonObject jsonObject1 = (JsonObject) parser.parse(json);
        String nickname = jsonObject1.get("nickname").getAsString();
        String city = jsonObject1.get("city").getAsString();
        String province = jsonObject1.get("province").getAsString();
        String country = jsonObject1.get("country").getAsString();
        String headimgurl = jsonObject1.get("headimgurl").getAsString();
        String openid = jsonObject1.get("openid").getAsString();
        //int subscribe = jsonObject1.get("subscribe").getAsInt();
        Integer sex = jsonObject1.get("sex").getAsInt();
        SpMemberWx wx = new SpMemberWx();
        int now = (int) (System.currentTimeMillis() / 1000);
        wx.setAddtime(now);
        wx.setImg(headimgurl);
        wx.setNickname(nickname);
        wx.setOpenid(openid);
        wx.setSubscribe(true);
        wxMapper.insertSelective(wx);
    }
}
