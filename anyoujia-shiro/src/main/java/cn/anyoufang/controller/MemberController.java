package cn.anyoufang.controller;


import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.service.MemberService;
import cn.anyoufang.utils.IPUtils;
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

/**
 * @author daiping
 */
@Api(value = "member")
@RestController
public class MemberController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;


    /**
     * 注册
     * @param phone
     * @param code
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping("/regist")
    @ApiOperation(value = "注册用户信息", httpMethod = "GET", notes = "member regist", response = Boolean.class)
    public boolean register(@ApiParam(required = true, value = "用户手机号", name = "phone") @RequestParam String phone,
                            @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                            @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        WeiXinVO weiXinVO = null;
        if (session != null) {
            weiXinVO = (WeiXinVO) session.getAttribute("weiXinVO");
            if (weiXinVO == null) {
                return false;
            }
        }
        String cacheCode = RedisUtils.get(phone);
        if (cacheCode == null) {
            return false;
        }
        code = code.trim();
        if (!code.equals(cacheCode)) {
            return false;
        }
        return memberService.memberRegister(phone, pwd, weiXinVO);
    }

    /**
     * 用户密码登录
     * @param account
     * @param pwd
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(value = "用户密码登录", httpMethod = "POST", notes = "member login", response = SpMember.class)
    public SpMember loginByPassword(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                    @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                                    HttpServletRequest request) {
        return memberService.memberLoginByPwd(account, pwd, IPUtils.getIpAddr(request));
    }


    /**
     * 手机验证码登录
     * @param account
     * @param code
     * @param request
     * @return
     */
    @RequestMapping("/loginbycode")
    @ApiOperation(value = "用户手机验证码登录", httpMethod = "POST", notes = "member login", response = SpMember.class)
    public SpMember loginByVerifyCode(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                      @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                                      HttpServletRequest request) {
        return memberService.memberLoginByVerifyCode(account, code, IPUtils.getIpAddr(request));
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ApiOperation(value = "用户登出", httpMethod = "GET", notes = "member loginout")
    public void logout() {
        memberService.memberLogout();
    }

    /**
     * 获取验证码
     * @param phone
     */
    @RequestMapping("/verifycode")
    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "member get code")
    public void verifyCode(@RequestParam String phone) {
        try {
            memberService.getVerifCode(phone);
        } catch (ClientException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
    }

    /**
     * 未登录状态下重置密码
     */

    @RequestMapping("/reset")
    @ApiOperation(value = "未登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public void resetPassword(@RequestParam String phone, @RequestParam String newpwd) {
        memberService.resetPwd(phone, newpwd);
    }

    /**
     * 已经登录状态下重置密码
     */
    @RequestMapping("/loginreset")
    @ApiOperation(value = "登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public boolean resetPwd(@RequestParam String oldPwd, @RequestParam String newPwd) {
        if (!isLogin()) {
            return false;
        }
        return memberService.resetPasswordLogined(oldPwd, newPwd);
    }

    @RequestMapping("/securitypwd")
    @ApiOperation(value = "设置安全密码", httpMethod = "POST", notes = "member set security password")
    public AnyoujiaResult setSecurityPwd(@RequestParam String password){
        if(StringUtil.isEmpty(password)) {
            return AnyoujiaResult.build(400,"请输入有效密码",null);
        }
       SpMember  user =  getUser();
       if(user !=null) {
           int id = user.getUid();
           if(memberService.setSecurityPwd(password,id)){
               return new AnyoujiaResult("");
           }
       }
       return AnyoujiaResult.build(400,"用户尚未登录",null);
    }
}
