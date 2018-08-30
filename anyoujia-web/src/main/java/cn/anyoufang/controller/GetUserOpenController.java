package cn.anyoufang.controller;

import cn.anyoufang.entity.WeiXinVO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/weixinInfo")
public class GetUserOpenController {
    //获取用户的openid
    @SuppressWarnings({ "resource", "deprecation" })
    @RequestMapping("getCode")
    public ModelAndView getCode(HttpServletRequest request, String code,
            String myUrl) throws ClientProtocolException, IOException {
        System.out.println(code);
        if (code != null) {
            //获取openid和access_token的连接
            String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb4d2ad46ec36fb06&secret=1f97ac7edc4589a892f27f71dfbe2a5b&code=CODE&grant_type=authorization_code";
            //获取返回的code
            String requestUrl = getOpenIdUrl.replace("CODE", code);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //向微信发送请求并获取response
            String response = httpClient.execute(httpGet, responseHandler);
            System.out.println(
                "=========================获取token===================");
            System.out.println(response);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(response);
            String access_token = jsonObject.get("access_token").getAsString();
            String openId = jsonObject.get("openid").getAsString();
            System.out.println(
                "=======================用户access_token==============");
            System.out.println(access_token);
            System.out.println(openId);
            //获取用户基本信息的连接
            String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            String userInfoUrl = getUserInfo
                    .replace("ACCESS_TOKEN", access_token)
                    .replace("OPENID", openId);
            HttpGet httpGetUserInfo = new HttpGet(userInfoUrl);
            String userInfo = httpClient.execute(httpGetUserInfo,
                responseHandler);
            //微信那边采用的编码方式为ISO8859-1所以需要转化
            String json = new String(userInfo.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(
                "====================userInfo==============================");
            JsonObject jsonObject1 = (JsonObject) parser.parse(json);
            String nickname = jsonObject1.get("nickname").getAsString();
            String city = jsonObject1.get("city").getAsString();
            String province = jsonObject1.get("province").getAsString();
            String country = jsonObject1.get("country").getAsString();
            String headimgurl = jsonObject1.get("headimgurl").getAsString();
            String openid = jsonObject1.get("openid").getAsString();
            Integer sex = jsonObject1.get("sex").getAsInt();
            WeiXinVO weiXinVO = new WeiXinVO(nickname, city, province, country,
                    headimgurl, openid, sex);
            HttpSession session = request.getSession();
            session.setAttribute("weiXinVO", weiXinVO);
            //性别  1 男  2 女  0 未知
            System.out.println("昵称" + nickname);
            System.out.println("城市" + city);
            System.out.println("省" + province);
            System.out.println("国家" + country);
            System.out.println("头像" + headimgurl);
            System.out.println("性别" + sex);
            System.out.println("openid" + openid);
            System.out.println(userInfo);
        }
        System.out.println(myUrl);
        ModelAndView myIndex = new ModelAndView(new RedirectView(myUrl));
        return myIndex;
    }
}
