package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpMemberWx;
import cn.anyoufang.entity.SpMemberWxExample;
import cn.anyoufang.entity.WeiXinVO;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUserServiceImpl.class);
    @Autowired
    private SpMemberWxMapper wxMapper;


    @Override
    public WeiXinVO getAndSaveUserInfoFromWx(String code)
            throws IOException {
        if (code != null) {
            //获取openid和session_key的连接 https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
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
            String sessionKey = jsonObject.get("session_key").getAsString();
            String openId = jsonObject.get("openid").getAsString();
            SpMemberWxExample example = new SpMemberWxExample();
            SpMemberWxExample.Criteria criteria = example.createCriteria();
            criteria.andOpenidEqualTo(openId);
            List<SpMemberWx> list = wxMapper.selectByExample(example);
            int wxid;
            if (list == null || list.size() == 0) {
                SpMemberWx wx = new SpMemberWx();
                int now = (int) (System.currentTimeMillis() / 1000);
                wx.setAddtime(now);
                wx.setOpenid(openId);
                wx.setSubscribe(true);
                wxid = wxMapper.insertSelective(wx);
                WeiXinVO weiXinVO = new WeiXinVO();
                weiXinVO.setOpenid(openId);
               return  weiXinVO;
            } else {
                SpMemberWx user = list.get(0);
                wxid = user.getWx();
            }


//            //获取用户基本信息的连接
//            String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//            String userInfoUrl = getUserInfo
//                    .replace("ACCESS_TOKEN", accessToken)
//                    .replace("OPENID", openId);
//            HttpGet httpGetUserInfo = new HttpGet(userInfoUrl);
//            String userInfo = httpClient.execute(httpGetUserInfo,
//                    responseHandler);
//            //微信那边采用的编码方式为ISO8859-1所以需要转化
//            String json = new String(userInfo.getBytes("ISO-8859-1"), "UTF-8");
//            JsonObject jsonObject1 = (JsonObject) parser.parse(json);
//            String nickname = jsonObject1.get("nickname").getAsString();
//            String city = jsonObject1.get("city").getAsString();
//            String province = jsonObject1.get("province").getAsString();
//            String country = jsonObject1.get("country").getAsString();
//            String headimgurl = jsonObject1.get("headimgurl").getAsString();
//            String openid = jsonObject1.get("openid").getAsString();
//            Integer sex = jsonObject1.get("sex").getAsInt();
//            SpMemberWxExample example = new SpMemberWxExample();
//            SpMemberWxExample.Criteria criteria = example.createCriteria();
//            criteria.andOpenidEqualTo(openid);
//            List<SpMemberWx> list = wxMapper.selectByExample(example);
//            int wxid;
//            if (list == null || list.size() == 0) {
//                SpMemberWx wx = new SpMemberWx();
//                int now = (int) (System.currentTimeMillis() / 1000);
//                wx.setAddtime(now);
//                wx.setImg(headimgurl);
//                wx.setNickname(nickname);
//                wx.setOpenid(openid);
//                wx.setSubscribe(true);
//                wxid = wxMapper.insert(wx);
//
//            } else {
//                SpMemberWx user = list.get(0);
//                wxid = user.getWx();
//            }
//
//            WeiXinVO weiXinVO = new WeiXinVO(nickname, city, province, country,
//                    headimgurl, openid, sex);
//            weiXinVO.setWxid(wxid);
//            return weiXinVO;
        }
        return null;
    }

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
