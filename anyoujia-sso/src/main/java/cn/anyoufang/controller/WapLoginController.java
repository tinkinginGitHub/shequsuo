//package cn.anyoufang.controller;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.aliyuncs.exceptions.ClientException;
//
//import cn.anyoufang.entity.AnyoufangResult;
//import cn.anyoufang.entity.WeiXinVO;
//import cn.anyoufang.service.WapLoginService;
//import cn.anyoufang.utils.JsonUtils;
//import cn.anyoufang.utils.JwtHelper;
//import io.jsonwebtoken.Claims;
//
//
//@RestController
//@RequestMapping("/api")
//public class WapLoginController {
//    private Logger logger = LoggerFactory.getLogger(WapLoginController.class);
//
//    @Autowired
//    private WapLoginService wapLoginService;
//
//    @Value("${TOKEN_KEY}")
//    private String TOKEN_KEY;
//
//
//    @RequestMapping("/verifycode")
//    public AnyoufangResult getVerifyCode(@RequestBody String json,HttpServletRequest request)  {
//
//        Map map = JsonUtils.jsonToMap(json);
//        String phone = (String) map.get("phone");
//        if(phone !=null) {
//            phone = phone.replaceAll("\"","");
//        }else {
//            String auth = request.getHeader("Authorization");
//           TbRenter user = wapLoginService.getUser(auth);
//           if(user != null) {
//               phone = user.getPhone();
//           }else {
//               return AnyoufangResult.build(400,"登录已过期");
//           }
//        }
//        if("".equals(phone.trim()) || phone == null) {
//
//            return AnyoufangResult.build(400,"号码不能为空");
//        }
//        String code = null;
//        try {
//
//           code  =  wapLoginService.getVerifyCode(phone);
//           if(code == null) {
//               return AnyoufangResult.build(400,"对不起，您已超过当日登录次数");
//           }
//
//        }catch (ClientException e) {
//            logger.info(e.getMessage());
//        }
//        return AnyoufangResult.ok();
//    }
//
//    @RequestMapping("/login")
//    public AnyoufangResult doLogin(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//        WeiXinVO weiXinVO = (WeiXinVO) request.getSession()
//                .getAttribute("weiXinVO");
//       // System.out.println(weiXinVO.getOpenid());
//        //{"phone":"12345678901","code":451256}
//         AnyoufangResult result = wapLoginService.login(json,false,weiXinVO);
//         Map data = new HashMap<>();
//         //登录成功后写cookie
//        if (result.getStatus() == 200) {
//            data.put("Authorization",JwtHelper.createJWT(result.getData().toString(),"user","user"));
//
//            result.setData(data);
//
//        }
//
//        return result;
//    }
//
//    @RequestMapping("/logout")
//    public AnyoufangResult doLogout(HttpServletRequest request,HttpServletResponse response) {
//        String authorization = request.getHeader("Authorization");
//        String token = wapLoginService.getToken(authorization);
//        if(token != null) {
//            return wapLoginService.logout(token);
//        }
//        return AnyoufangResult.ok();
//    }
//
//    @RequestMapping("/changephone")
//    public AnyoufangResult doChange(HttpServletRequest request,@RequestBody String json,HttpServletResponse response) {
//        String authorization = request.getHeader("Authorization");
//        Claims claims = JwtHelper.parseJWT(authorization);
//        String token = "";
//        if(claims != null) {
//            token = (String) claims.get("token");
//        }
//       AnyoufangResult result =  wapLoginService.changePhone(json,token);
//        Map data = new HashMap<>();
//        //登录成功
//        if (result.getStatus() == 200) {
//            data.put("Authorization",JwtHelper.createJWT(result.getData().toString(),"user","user"));
//            result.setData(data);
//        }
//        return result;
//    }
//    @RequestMapping("/binding")
//    public AnyoufangResult bindingVerify(@RequestBody String code,HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        String token = wapLoginService.getToken(authorization);
//        return wapLoginService.doVerify(code,token);
//    }
//
//    @RequestMapping("/isLogin")
//    public String isLogin(HttpServletRequest request) {
//      String authorization = request.getHeader("Authorization");
//        WeiXinVO weiXinVO = (WeiXinVO) request.getSession()
//                .getAttribute("weiXinVO");
//        String openId = null;
//        if(weiXinVO != null) {
//             openId = weiXinVO.getOpenid();
//        }
//        return wapLoginService.isLogin(authorization,openId);
//    }
//    @RequestMapping("/isPhoneExist/{phone}")
//    public boolean isPhoneExist(@PathVariable String phone) {
//       return wapLoginService.isPhoneExist(phone);
//    }
//}
