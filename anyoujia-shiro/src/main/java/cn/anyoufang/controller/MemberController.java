package cn.anyoufang.controller;


import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.Null;
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
@RequestMapping("/api")
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
    public AnyoujiaResult register(@ApiParam(required = true, value = "用户手机号", name = "phone") @RequestParam String phone,
                            @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                            @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        WeiXinVO weiXinVO = null;
        if (session != null) {
            weiXinVO = (WeiXinVO) session.getAttribute("weiXinVO");
            if (weiXinVO != null) {

            }else {
                String cacheCode = RedisUtils.get(phone);
                if (cacheCode == null) {
                    return AnyoujiaResult.build(400,"verifycode overtime");
                }
                code = code.trim();
                if (!code.equals(cacheCode)) {
                    return AnyoujiaResult.build(400,"wrong verifycode");
                }
            }
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
    public AnyoujiaResult loginByPassword(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                    @ApiParam(required = true, value = "密码", name = "pwd") @RequestParam String pwd,
                                    HttpServletRequest request) {
       SpMember user =  memberService.memberLoginByPwd(account, pwd, IPUtils.getIpAddr(request));
       if(user instanceof Null) {
           return AnyoujiaResult.build(400,"account not exist");
       }
       if(user != null) {
           return AnyoujiaResult.ok(user);
       }
        return AnyoujiaResult.build(400,"wrong account or password");
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
    public AnyoujiaResult loginByVerifyCode(@ApiParam(required = true, value = "手机号", name = "account") @RequestParam String account,
                                      @ApiParam(required = true, value = "验证码", name = "code") @RequestParam String code,
                                      HttpServletRequest request) {
        SpMember  user = memberService.memberLoginByVerifyCode(account, code, IPUtils.getIpAddr(request));
        if(user instanceof Null) {
            return AnyoujiaResult.build(400,"account not exist");
        }
        if(user == null) {
            return AnyoujiaResult.build(400,"verify overtime");
        }
        return AnyoujiaResult.ok(user);
    }

    @RequestMapping("/addtioninfo")
    public AnyoujiaResult additionalInfo(@RequestParam String avatar,
                                         @RequestParam String bname,
                                         @RequestParam String phone) {
          if(memberService.updateAdditionalUserInfo(avatar,bname,phone)) {
              return AnyoujiaResult.ok();
          }
          return AnyoujiaResult.build(400,"update failed");
    }

    @RequestMapping("/checkSe")
    public AnyoujiaResult checkSetSecurityPwd() {
        SpMember user = getUser();
        if(user != null && null !=user.getSecuritypwd()) {
            return AnyoujiaResult.ok();
        }
        return AnyoujiaResult.build(400,"no security password yet");
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ApiOperation(value = "用户登出", httpMethod = "GET", notes = "member loginout")
    public AnyoujiaResult logout() {
        memberService.memberLogout();
        return AnyoujiaResult.build(200,"success");
    }

    /**
     * 获取验证码
     * @param phone
     */
    @RequestMapping("/verifycode")
    @ApiOperation(value = "获取验证码", httpMethod = "POST", notes = "member get code")
    public AnyoujiaResult verifyCode(@RequestParam String phone) {
        try {
            memberService.getVerifCode(phone);
        } catch (ClientException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return AnyoujiaResult.build(200,"success");
    }

    /**
     * 未登录状态下重置密码
     */

    @RequestMapping("/reset")
    @ApiOperation(value = "未登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public AnyoujiaResult resetPassword(@RequestParam String phone, @RequestParam String newpwd) {
       if(memberService.resetPwd(phone, newpwd)) {
           return AnyoujiaResult.build(200,"success");
       }
       return AnyoujiaResult.build(400,"reset failed");
    }

    /**
     * 已经登录状态下重置密码
     */
    @RequestMapping("/loginreset")
    @ApiOperation(value = "登录状态下重置密码", httpMethod = "POST", notes = "member reset password")
    public AnyoujiaResult resetPwd(@RequestParam String oldPwd, @RequestParam String newPwd) {
        if (!isLogin()) {
            return AnyoujiaResult.build(400,"please do login");
        }
       boolean isresetOk =  memberService.resetPasswordLogined(oldPwd, newPwd);
        if(isresetOk) {
            return AnyoujiaResult.build(200,"success");
        }
        return AnyoujiaResult.build(400,"reset fail");
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
                                         @RequestParam String answer){
        if(StringUtil.isEmpty(password)) {
            return AnyoujiaResult.build(400,"password is invalid");
        }
        if(StringUtil.isEmpty(answer) || StringUtil.isEmpty(question)) {
            return AnyoujiaResult.build(400,"no answer or question find");
        }
       SpMember  user =  getUser();
       if(user !=null) {
           int id = user.getUid();
           if(memberService.setSecurityPwd(password,id,question,answer)){
               return AnyoujiaResult.build(200,"success");
           }
       }
       return AnyoujiaResult.build(400,"not login");
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
                                           @RequestParam String question,
                                           @RequestParam String answer) {
        if(StringUtil.isEmpty(oldPwd) ||StringUtil.isEmpty(newPwd) ) {
            return AnyoujiaResult.build(400,"password is invalid");
        }
        SpMember  user =  getUser();
        if(user !=null) {
            int id = user.getUid();
            if(memberService.updateSecurityPwd(oldPwd,newPwd,id,answer,question)){
                return AnyoujiaResult.build(200,"success");
            }
        }
        return AnyoujiaResult.build(400,"not login");
    }

    /**
     * 找回密码之前检查账户
     * @param phone
     * @param code
     * @return
     */

    @RequestMapping("/findpwd")
    @ApiOperation(value = "找回密码之前检查账户", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult checkAndfindPwd(@RequestParam String phone,@RequestParam String code) {
        boolean existAccount = memberService.checkAccount(phone,code);
        if(existAccount) {
            return AnyoujiaResult.build(200,"success");
        }
        return AnyoujiaResult.build(400,"account not exist");
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
                                                  @RequestParam String answer) {

        boolean existAccount = memberService.checkAccountAndAnswer(phone,code,answer);
        if(existAccount) {
            return AnyoujiaResult.build(200,"success");
        }
        return AnyoujiaResult.build(400,"account not exist");

    }

    /**
     * 获取用户选择的安全问题
     * @return
     */
    @RequestMapping("/question.do")
    @ApiOperation(value = "获取用户选择的安全问题", httpMethod = "POST", notes = "member check account")
    public AnyoujiaResult getUserCheckedQuestion() {
        SpMember user = getUser();
        if(user != null) {
            String question = user.getSecurityquestion();
            return AnyoujiaResult.build(200,"success",question);
        }
        return AnyoujiaResult.build(400,"please do login");
    }

}
