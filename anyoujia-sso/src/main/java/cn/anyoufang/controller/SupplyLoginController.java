package cn.anyoufang.controller;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.anyoufang.annotation.AccessToken;
import cn.anyoufang.entity.AnyoufangResult;
import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.service.SupplyLoginService;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.ShiroUtils;

@RestController
@RequestMapping("/supply")
public class SupplyLoginController {
    private Logger logger = LoggerFactory.getLogger(SupplyLoginController.class);

    @Autowired
    private SupplyLoginService supplyLoginService;

    /**
     * 供应商端登录请求处理
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public AnyoufangResult doLogin(@RequestBody String userinfo, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = JsonUtils.jsonToMap(userinfo);
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String id = supplyLoginService.login(username,password);
        if(!id.equals("")) {
            request.getSession().setAttribute("username",id);
            Subject currentUser = ShiroUtils.getSubject();
            if ( !currentUser.isAuthenticated() ) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(true);
                currentUser.login(token);
            }
            if(currentUser.isAuthenticated()) {
                logger.info("用户 [" + currentUser.getPrincipal() + "] 登陆成功");
                return AnyoufangResult.ok();
            }

            return AnyoufangResult.build(401,"用户名或者密码不对");
        }else {
           return AnyoufangResult.build(401,"用户名或者密码不对");
        }
    }
    @RequestMapping("/logout")
    @AccessToken
    public AnyoufangResult logout(HttpServletRequest request) {
       request.getSession().removeAttribute("username");
        ShiroUtils.getSubject().logout();
        return AnyoufangResult.ok();
    }

}
