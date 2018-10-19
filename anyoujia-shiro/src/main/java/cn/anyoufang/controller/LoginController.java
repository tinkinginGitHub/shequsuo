package cn.anyoufang.controller;


import cn.anyoufang.annotation.AccessToken;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.Null;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.service.WxUserService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Autowired
    private WxUserService wxUserService;


    /**
     * 注册
     */
    @RequestMapping("/regist")
    @ApiOperation(value = "注册用户信息", httpMethod = "GET", notes = "member regist", response = AnyoujiaResult.class)
    public AnyoujiaResult register(@ApiParam(required = true, value = "用户手机号", name = "phone") @RequestParam String phone,
                                   @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                                   @RequestParam String wxcode,
                                   @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                                   HttpServletRequest request) {


        if(StringUtil.isEmpty(code)) {
            return AnyoujiaResult.build(400, "验证码错误");
        }
        if(StringUtil.isEmpty(pwd)) {
            return AnyoujiaResult.build(400, "密码错误");
        }

        if(StringUtil.isEmpty(phone)) {
            return AnyoujiaResult.build(400, "手机号错误");
        }
        String cacheCode = RedisUtils.get(Md5Utils.md5(phone,"utf-8"));
        if (cacheCode == null) {
            return AnyoujiaResult.build(400, "验证码超时");
        }
        code = code.trim();
        if (!code.equals(cacheCode)) {
            return AnyoujiaResult.build(400, "验证码错误");
        }

        String openId;
        try {
            openId = wxUserService.getOpenId(wxcode);

        } catch (IOException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(500, "系统错误");
        }

        try {
            return loginService.memberRegister(phone, pwd, openId);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(400, "注册失败");
        }
    }


    /**
     * 用户密码登录
     */
    @RequestMapping("/login")
    @ApiOperation(value = "用户密码登录", httpMethod = "POST", notes = "member login", response = AnyoujiaResult.class)
    public AnyoujiaResult loginByPassword(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                          @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                                          HttpServletRequest request) {
        Map<String, Object> res;
        try {
            res = loginService.memberLoginByPwd(account, pwd, IPUtils.getIpAddr(request));
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(500, "系统异常");
        }
        if (res != null) {
            return AnyoujiaResult.ok(res);
        }

        if (res instanceof Null) {
            return AnyoujiaResult.build(400, "账号不存在");
        }
        return AnyoujiaResult.build(400, "账号或者密码错误");
    }

    /**
     * 手机验证码登录
     */
    @RequestMapping("/loginbycode")
    @ApiOperation(value = "用户手机验证码登录", httpMethod = "POST", notes = "member login", response = AnyoujiaResult.class)
    public AnyoujiaResult loginByVerifyCode(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                            @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                                            HttpServletRequest request) {

        Map<String, Object> res;
        try {
            res = loginService.memberLoginByVerifyCode(account, code, IPUtils.getIpAddr(request));
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(500, "系统异常");
        }
        if (res instanceof Null) {
            return AnyoujiaResult.build(400, "账号不存在");
        }
        if (res == null) {
            return AnyoujiaResult.build(400, "验证码超时或错误");
        }
        return AnyoujiaResult.ok(res);
    }

    @RequestMapping("/addtioninfo")
    public AnyoujiaResult additionalInfo(@RequestParam String avatar,
                                         @RequestParam String bname,
                                         @RequestParam String phone,HttpServletRequest request) {
        if(StringUtil.isEmpty(avatar)
                || StringUtil.isEmpty(bname)
                ||StringUtil.isEmpty(phone)) {

         return AnyoujiaResult.build(400,"参数异常");
        }
        boolean addOk;
        try {
           addOk = loginService.updateAdditionalUserInfo(avatar, bname, phone);
            if (addOk) {
                return AnyoujiaResult.ok();
            }
        }catch (RuntimeException e){
            if(logger.isInfoEnabled()) {
               logger.info(e.getMessage());
            }
            return AnyoujiaResult.build(400, "更新失败");
        }

        return AnyoujiaResult.build(400, "更新失败");
    }

    @RequestMapping("/checkSe")
    public AnyoujiaResult checkSetSecurityPwd(HttpServletRequest request) {
        SpMember user = getUser(request, loginService);
        if (user != null) {
            String securityPwd = user.getSecuritypwd();
            if (securityPwd != null && securityPwd.trim().length() > 0) {
                return AnyoujiaResult.ok();
            }
        }
        return AnyoujiaResult.build(400, "您还未设置安全密码");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ApiOperation(value = "用户登出", httpMethod = "GET", notes = "member loginout")
    public AnyoujiaResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            loginService.memberLogout(token);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return AnyoujiaResult.build(200, "退出成功");
    }

    /**
     * 只允许一个地方登陆，此接口给php调用
     */
    @RequestMapping("/ssologout")
    public AnyoujiaResult ssoLogout(@RequestBody Map param) {
        String session = String.valueOf(param.get("wxHdp"));
        if (session == null || session.trim().length() == 0) {
            return AnyoujiaResult.build(400, "请输入正确参数");
        }
        try {
            loginService.memberLogout(session);
        } catch (Exception e) {
            return AnyoujiaResult.build(500, "系统错误");
        }
        //TODO 告诉前台被挤下线
        return AnyoujiaResult.ok();
    }


    /**
     * 获取验证码
     */
    @RequestMapping("/verifycode")
    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "member get code")
    public AnyoujiaResult verifyCode(@RequestParam String phone) {

        String res = "";
        try {
            res =  loginService.sendVerifCode(phone);
        } catch (ClientException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(400, "获取验证码失败");
        }
        if("true".equals(res)) {
            return AnyoujiaResult.build(200, "获取验证码成功");
        }else if("false".equals(res)) {
            return AnyoujiaResult.build(400, "获取验证码失败");
        }else {
            return AnyoujiaResult.build(400, "您短信使用次数已达最大次数");
        }


    }

    /**
     * 未登录状态下重置密码
     */

    @RequestMapping("/reset")
    @ApiOperation(value = "未登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public AnyoujiaResult resetPassword(@RequestParam String phone, @RequestParam String newpwd) {
        try {
            boolean ok = loginService.resetPwd(phone, newpwd);
            if (ok) {
                return AnyoujiaResult.build(200, "重置成功");
            }
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(400, "重置失败");
        }

        return AnyoujiaResult.build(400, "重置失败");
    }

    /**
     * 已经登录状态下重置密码
     */
    @RequestMapping("/loginreset")
    @ApiOperation(value = "登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    @AccessToken
    public AnyoujiaResult resetPwd(@RequestParam String oldPwd, @RequestParam String newPwd, HttpServletRequest request) {

        if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(newPwd)) {
            AnyoujiaResult.build(400, "请输入正确密码");
        }
        if (!isLogin(request.getHeader("token"))) {
            return AnyoujiaResult.build(401, "请重新登录");
        }
        updateLogin(request, loginService);

        SpMember user = getUser(request, loginService);

        if (user == null) {
            return AnyoujiaResult.build(401, "请登录");
        }
        String oldPassword = user.getPassword();
        if (!oldPassword.equals(Md5Utils.md5(oldPwd.trim(), "utf-8"))) {
            return AnyoujiaResult.build(400, "请输入正确密码");
        }
        String phone = user.getPhone();
        boolean isresetOk;
        try {
            isresetOk = loginService.resetPasswordLogined(phone, newPwd);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(400, "重置失败");
        }
        if (isresetOk) {
            return AnyoujiaResult.build(200, "重置成功");
        }
        return AnyoujiaResult.build(400, "重置失败");
    }

    /**
     * 设置安全密码
     */

    @RequestMapping("/securitypwd")
    @ApiOperation(value = "设置安全密码", httpMethod = "POST", notes = "member set security password")
    public AnyoujiaResult setSecurityPwd(@RequestParam String password,
                                         @RequestParam String question,
                                         @RequestParam String answer, HttpServletRequest request) {
        if (StringUtil.isEmpty(password)) {
            return AnyoujiaResult.build(400, "安全密码无效");
        }
        if (StringUtil.isEmpty(answer) || StringUtil.isEmpty(question)) {
            return AnyoujiaResult.build(400, "无效安全问题");
        }
        SpMember user = getUser(request, loginService);
        if (user != null) {
            updateLogin(request, loginService);
            int id = user.getUid();
            if (loginService.setSecurityPwd(password, id, question, answer)) {
                return AnyoujiaResult.build(200, "设置成功");
            }
        }
        return AnyoujiaResult.build(401, "请登录");
    }

    /**
     * 设置安全密码
     */
    @RequestMapping("/resetsecurpwd")
    @ApiOperation(value = "重置安全密码", httpMethod = "POST", notes = "member reset security password")
    public AnyoujiaResult resetSecurityPwd(@RequestParam String oldPwd,
                                           @RequestParam String newPwd,
                                           HttpServletRequest request) {

        if (StringUtil.isEmpty(oldPwd) || StringUtil.isEmpty(newPwd)) {
            return AnyoujiaResult.build(400, "安全密码无效");
        }
        SpMember user = getUser(request, loginService);
        if (user != null) {
            updateLogin(request, loginService);
            int id = user.getUid();
            if (loginService.updateSecurityPwd(oldPwd, newPwd, id)) {
                return AnyoujiaResult.build(200, "重置成功");
            }
        }
        return AnyoujiaResult.build(401, "请重新登录");
    }

    /**
     * 找回密码之前检查账户
     */

    @RequestMapping("/findpwd")
    @ApiOperation(value = "找回密码之前检查账户", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult checkAndfindPwd(@RequestParam String phone, @RequestParam String code, HttpServletRequest request) {
        try {
            updateLogin(request, loginService);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(401, "找回密码失败");
        }
        boolean existAccount = loginService.checkAccount(phone, code);
        if (existAccount) {
            return AnyoujiaResult.build(200, "找回 成功");
        }
        return AnyoujiaResult.build(400, "账户不存在");
    }

    /**
     * 找回安全密码之前的检查
     */
    @RequestMapping("/findscpwd")
    @ApiOperation(value = "找回安全密码之前检查", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult checkAndfindSecurityPwd(@RequestParam String phone,
                                                  @RequestParam String code,
                                                  @RequestParam String answer, HttpServletRequest request) {

        updateLogin(request, loginService);
        if (!loginService.checkAccount(phone)) {
            return AnyoujiaResult.build(400, "账号不存在");
        }
        if (!loginService.checkByCode(phone, code)) {
            return AnyoujiaResult.build(400, "验证码错误");
        }
        boolean existAccount = loginService.checkAccountAndAnswer(phone, code, answer);
        if (existAccount) {
            return AnyoujiaResult.build(200, "找回成功");
        }

        return AnyoujiaResult.build(400, "安全问题回答错误");

    }

    /**
     * 获取用户选择的安全问题
     */
    @RequestMapping("/question.do")
    @ApiOperation(value = "获取用户选择的安全问题", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult getUserCheckedQuestion(HttpServletRequest request) {
        SpMember user = getUser(request, loginService);
        if (user != null) {
            updateLogin(request, loginService);
            String question = user.getSecurityquestion();
            return AnyoujiaResult.build(200, "success", question);
        }
        return AnyoujiaResult.build(401, "请重新登录");
    }

    @RequestMapping("/islogin")
    public AnyoujiaResult isLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        try {
            if (token == null || !isLogin(token)) {
                return AnyoujiaResult.build(401, "请登录");
            }
            updateLogin(request, loginService);
        } catch (Exception e) {
            AnyoujiaResult.build(401, "请登录");
        }

        return AnyoujiaResult.ok();
    }

    @RequestMapping("/delsecurity")
    public AnyoujiaResult removeSecurityPwd(HttpServletRequest request) {
        String token = request.getHeader("token");

        try {
            updateLogin(request, loginService);
            if (token == null || !isLogin(token)) {
                return AnyoujiaResult.build(401, "请登录");
            }
            SpMember user = getUser(request, loginService);
            if (user != null) {
                String phone = user.getPhone();
                if (loginService.delSecurityPassword(phone)) {
                    return AnyoujiaResult.ok();
                }
            }
        } catch (Exception e) {
            return AnyoujiaResult.build(401, "请登录");
        }
        return AnyoujiaResult.build(401, "请登录");
    }
}
