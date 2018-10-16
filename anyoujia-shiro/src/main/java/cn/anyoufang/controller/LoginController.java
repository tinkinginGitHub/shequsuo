package cn.anyoufang.controller;


import cn.anyoufang.annotation.AccessToken;
import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.Null;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.IPUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.RedisUtils;
import cn.anyoufang.utils.StringUtil;
import com.aliyuncs.exceptions.ClientException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @author daiping
 */
@Api(value = "Login")
@RestController
@RequestMapping("/api")
public class LoginController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;


    /**
     * 注册
     * @param phone
     * @param code
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping("/regist")
    @ApiOperation(value = "注册用户信息", httpMethod = "GET", notes = "member regist", response = AnyoujiaResult.class)
    public AnyoujiaResult register(@ApiParam(required = true, value = "用户手机号", name = "phone") @RequestParam String phone,
                            @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                            @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                            HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        WeiXinVO weiXinVO = null;
        if (session != null) {
            weiXinVO = (WeiXinVO) session.getAttribute("weiXinVO");
            if (weiXinVO != null) {

            }else {
                String cacheCode = RedisUtils.get(phone);
                if (cacheCode == null) {
                    return AnyoujiaResult.build(400,"验证码超时");
                }
                code = code.trim();
                if (!code.equals(cacheCode)) {
                    return AnyoujiaResult.build(400,"验证码错误");
                }
            }
        }
        return  loginService.memberRegister(phone, pwd,weiXinVO);
    }


    /**
     * 用户密码登录
     * @param account
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(value = "用户密码登录", httpMethod = "POST", notes = "member login", response = AnyoujiaResult.class)
    public AnyoujiaResult loginByPassword(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                    @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                                    HttpServletRequest request) throws Exception {
       Map<String,Object> user =  loginService.memberLoginByPwd(account, pwd, IPUtils.getIpAddr(request));
        if(user != null) {
            Integer status  = (Integer) user.get("status");
            String msg = (String) user.get("msg");
            if(status !=200) {
                return AnyoujiaResult.build(status,msg);
            }
            return AnyoujiaResult.ok(user);
        }

       if(user instanceof Null) {
           return AnyoujiaResult.build(400,"账号不存在");
       }
        return AnyoujiaResult.build(400,"账号或者密码错误");
    }

    /**
     * 手机验证码登录
     * @param account
     * @param code
     * @param request
     * @return
     */
    @RequestMapping("/loginbycode")
    @ApiOperation(value = "用户手机验证码登录", httpMethod = "POST", notes = "member login", response = AnyoujiaResult.class)
    public AnyoujiaResult loginByVerifyCode(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                      @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                                      HttpServletRequest request) throws Exception {

        Map<String,Object>  user = loginService.memberLoginByVerifyCode(account, code, IPUtils.getIpAddr(request));
        if(user instanceof Null) {
            return AnyoujiaResult.build(400,"账号不存在");
        }
        if(user == null) {
            return AnyoujiaResult.build(400,"验证码超时或错误");
        }
        return AnyoujiaResult.ok(user);
    }

    @RequestMapping("/addtioninfo")
    public AnyoujiaResult additionalInfo(@RequestParam String avatar,
                                         @RequestParam String bname,
                                         @RequestParam String phone) {
          if(loginService.updateAdditionalUserInfo(avatar,bname,phone)) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(400,"更新失败");
    }

    @RequestMapping("/checkSe")
    public AnyoujiaResult checkSetSecurityPwd(HttpServletRequest request) throws Exception {
        SpMember user = getUser(request, loginService);
        if(user != null ) {
          String securityPwd = user.getSecuritypwd();
            if(securityPwd !=null&& securityPwd.trim().length() > 0){
                return AnyoujiaResult.ok();
            }
        }
        return AnyoujiaResult.build(400,"您还未设置安全密码");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ApiOperation(value = "用户登出", httpMethod = "GET", notes = "member loginout")
    public AnyoujiaResult logout(HttpServletRequest request) throws Exception {
       String token =  request.getHeader("token");
        loginService.memberLogout(token);
        return AnyoujiaResult.build(200,"退出成功");
    }

    @RequestMapping("/ssologout")
    public AnyoujiaResult ssoLogout(HttpServletRequest request) throws Exception {
       Map<String,String[]> data =  request.getParameterMap();
        BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String xmlHead = "";
        String xmlContent="";
        String line = null;
        while ((line = in.readLine()) != null) {
           sb.append(line);
        }
        System.out.println(sb.toString());
       for(Map.Entry<String,String[]> s:data.entrySet()) {
           System.out.println(s.getKey() + ": " + s.getValue());
       }
        String session = request.getParameter("wxHdp");
        System.out.println(session);
        System.out.println(request.getInputStream().toString());
        if(session == null || session.trim().length() == 0) {
            return AnyoujiaResult.build(400,"请输入正确参数");
        }
        System.out.println(session);
        loginService.memberLogout(session);
        return AnyoujiaResult.ok();
    }


    /**
     * 获取验证码
     * @param phone
     */
    @RequestMapping("/verifycode")
    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "member get code")
    public AnyoujiaResult verifyCode(@RequestParam String phone) {
        try {
            loginService.getVerifCode(phone);
        } catch (ClientException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return AnyoujiaResult.build(200,"获取验证码成功");
    }

    /**
     * 未登录状态下重置密码
     */

    @RequestMapping("/reset")
    @ApiOperation(value = "未登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public AnyoujiaResult resetPassword(@RequestParam String phone, @RequestParam String newpwd) throws Exception {
       if(loginService.resetPwd(phone, newpwd)) {
           return AnyoujiaResult.build(200,"重置成功");
       }
       return AnyoujiaResult.build(400,"重置失败");
    }

    /**
     * 已经登录状态下重置密码
     */
    @RequestMapping("/loginreset")
    @ApiOperation(value = "登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    @AccessToken
    public AnyoujiaResult resetPwd(@RequestParam String oldPwd, @RequestParam String newPwd,HttpServletRequest request) throws Exception {

        if(StringUtil.isEmpty(oldPwd) ||StringUtil.isEmpty(newPwd)) {
            AnyoujiaResult.build(400,"请输入正确密码");
        }

        if (!isLogin(request.getHeader("token"))) {
            return AnyoujiaResult.build(401,"请重新登录");
        }
        updateLogin(request, loginService);
        SpMember user = getUser(request, loginService);
        if(user == null) {
            return AnyoujiaResult.build(401,"请登录");
        }
        String oldPassword = user.getPassword();
        if(!oldPassword.equals(Md5Utils.md5(oldPwd.trim(),"utf-8"))) {
            return AnyoujiaResult.build(400,"请输入正确密码");
        }
        String phone = user.getPhone();
       boolean isresetOk =  loginService.resetPasswordLogined(phone, newPwd);
        if(isresetOk) {
            return AnyoujiaResult.build(200,"重置成功");
        }
        return AnyoujiaResult.build(400,"重置失败");
    }

    /**
     * 设置安全密码
     * @param password
     * @return
     */

    @RequestMapping("/securitypwd")
    @ApiOperation(value = "设置安全密码", httpMethod = "POST", notes = "member set security password")
    public AnyoujiaResult setSecurityPwd(@RequestParam String password,
                                         @RequestParam String question,
                                         @RequestParam String answer,HttpServletRequest request) throws Exception {
        if(StringUtil.isEmpty(password)) {
            return AnyoujiaResult.build(400,"安全密码无效");
        }
        if(StringUtil.isEmpty(answer) || StringUtil.isEmpty(question)) {
            return AnyoujiaResult.build(400,"无效安全问题");
        }
       SpMember  user =  getUser(request, loginService);
       if(user !=null) {
           updateLogin(request, loginService);
           int id = user.getUid();
           if(loginService.setSecurityPwd(password,id,question,answer)){
               return AnyoujiaResult.build(200,"设置成功");
           }
       }
       return AnyoujiaResult.build(401,"请登录");
    }

    /**
     * 设置安全密码
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping("/resetsecurpwd")
    @ApiOperation(value = "重置安全密码", httpMethod = "POST", notes = "member reset security password")
    public AnyoujiaResult resetSecurityPwd(@RequestParam String oldPwd,
                                           @RequestParam String newPwd,
                                           HttpServletRequest request) throws Exception {
        if(StringUtil.isEmpty(oldPwd) ||StringUtil.isEmpty(newPwd) ) {
            return AnyoujiaResult.build(400,"安全密码无效");
        }
        SpMember  user =  getUser(request, loginService);
        if(user !=null) {
            updateLogin(request, loginService);
            int id = user.getUid();
            if(loginService.updateSecurityPwd(oldPwd,newPwd,id)){
                return AnyoujiaResult.build(200,"重置成功");
            }
        }
        return AnyoujiaResult.build(401,"请重新登录");
    }

    /**
     * 找回密码之前检查账户
     * @param phone
     * @param code
     * @return
     */

    @RequestMapping("/findpwd")
    @ApiOperation(value = "找回密码之前检查账户", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult checkAndfindPwd(@RequestParam String phone,@RequestParam String code,HttpServletRequest request) {
        try {
            updateLogin(request, loginService);
        }catch (Exception e) {
            return AnyoujiaResult.build(401,"找回密码失败");
        }
        boolean existAccount = loginService.checkAccount(phone,code);
        if(existAccount) {
            return AnyoujiaResult.build(200,"找回 成功");
        }
        return AnyoujiaResult.build(400,"账户不存在");
    }

    /**
     * 找回安全密码之前的检查
     * @param phone
     * @param code
     * @param answer
     * @return
     */
    @RequestMapping("/findscpwd")
    @ApiOperation(value = "找回安全密码之前检查", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult checkAndfindSecurityPwd(@RequestParam String phone,
                                                  @RequestParam String code,
                                                  @RequestParam String answer,HttpServletRequest request) throws Exception {
        try {
            updateLogin(request, loginService);
        }catch (Exception e) {
            return AnyoujiaResult.build(401,"找回密码失败");
        }
        if(!loginService.checkAccount(phone)) {
            return AnyoujiaResult.build(400,"账号不存在");
        }
        if(!loginService.checkByCode(phone, code)){
            return AnyoujiaResult.build(400,"验证码错误");
        }
        boolean existAccount = loginService.checkAccountAndAnswer(phone,code,answer);
        if(existAccount) {
            return AnyoujiaResult.build(200,"找回成功");
        }

        return AnyoujiaResult.build(400,"安全问题回答错误");

    }

    /**
     * 获取用户选择的安全问题
     * @return
     */
    @RequestMapping("/question.do")
    @ApiOperation(value = "获取用户选择的安全问题", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult getUserCheckedQuestion(HttpServletRequest request) throws Exception {
        SpMember user = getUser(request, loginService);
        if(user != null) {
            updateLogin(request, loginService);
            String question = user.getSecurityquestion();
            return AnyoujiaResult.build(200,"success",question);
        }
        return AnyoujiaResult.build(401,"请重新登录");
    }

    @RequestMapping("/islogin")
    public AnyoujiaResult isLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        try{
            if(token == null || !isLogin(token)) {
                return AnyoujiaResult.build(401,"请登录");
            }
            updateLogin(request, loginService);
        }catch (Exception e) {
            AnyoujiaResult.build(401,"请登录");
        }

       return AnyoujiaResult.ok();
    }

    @RequestMapping("/delsecurity")
    public AnyoujiaResult removeSecurityPwd(HttpServletRequest request) {
        String token = request.getHeader("token");

        try{
            updateLogin(request, loginService);
            if(token == null || !isLogin(token)) {
                return AnyoujiaResult.build(401,"请登录");
            }
           SpMember user =  getUser(request,loginService);
            if(user !=null) {
              String phone =   user.getPhone();
                if(loginService.delSecurityPassword(phone)){
                         return AnyoujiaResult.ok();
                }
            }
        }catch (Exception e) {
           return  AnyoujiaResult.build(401,"请登录");
        }
        return  AnyoujiaResult.build(401,"请登录");
    }

}
