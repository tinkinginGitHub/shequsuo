//package cn.anyoufang.controller;
//
//import cn.anyoufang.entity.AnyoufangResult;
//import cn.anyoufang.service.AnyoufangBackLoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.DigestUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@RestController
//public class AnyoufangBackLoginController {
//
//    @Autowired
//    private AnyoufangBackLoginService backLoginService;
//
//    @RequestMapping("/ayfback/login")
//    public AnyoufangResult doLogin(String username, String pwd, HttpServletRequest request, HttpServletResponse response) {
//
//        String rememberMe = request.getParameter("rememberMe");
//        //1.记住我，否则不记住我
//        boolean keep = "1".equals(rememberMe) ? true : false;
//        boolean isOk = backLoginService.login(username,pwd);
//        if(isOk) {
//            if(keep) {
//                Cookie nameCookie = new Cookie("username",username);
//                Cookie passwordCookie = new Cookie("password", DigestUtils.md5DigestAsHex(pwd.getBytes()));
//                nameCookie.setPath(request.getContextPath()+"/");
//                passwordCookie.setPath(request.getContextPath()+"/");
//                nameCookie.setMaxAge(7*24*60*60);//保存一周
//                passwordCookie.setMaxAge(7*24*60*60);//保存一周
//
//                response.addCookie(nameCookie);
//                response.addCookie(passwordCookie);
//                request.setAttribute("username",username);
//                return AnyoufangResult.ok();
//            }
//            return AnyoufangResult.ok();
//
//        }else {
//            return AnyoufangResult.build(400,"用户名或者密码不对");
//        }
//
//    }
//
//}
