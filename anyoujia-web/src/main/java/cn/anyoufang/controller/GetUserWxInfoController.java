package cn.anyoufang.controller;

import cn.anyoufang.entity.Null;
import cn.anyoufang.entity.selfdefined.AnyoujiaResult;
import cn.anyoufang.entity.selfdefined.ResultWx;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.service.WxUserService;
import cn.anyoufang.utils.IPUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author daiping
 * @date 2018-09-05
 */
@Api(value = "member",description="获取用户微信基本信息")
@RestController
@RequestMapping("/weixinInfo")
public class GetUserWxInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserWxInfoController.class);

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private LoginService loginService;


    /**
     *  处理微信登陆
     */
    @SuppressWarnings({ "resource", "deprecation" })
    @RequestMapping("/getCode")
    @ApiOperation(value = "获取用户微信信息并保存",httpMethod = "POST",response = AnyoujiaResult.class)
    public AnyoujiaResult handleWxLogin(@RequestParam String code, HttpServletRequest request) {

      ResultWx res;
        try {

            res = wxUserService.CheckUserwXInfoBand(code);
        } catch (IOException e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(500,"系统错误");
        }
        if(res == null) {
            return AnyoujiaResult.build(401,"尚未绑定微信账号");
        }
        String account = res.getPhone();
        String pwd = res.getPassword();
        String loginIp = IPUtils.getIpAddr(request);
        Map<String, Object> loginResult;
        try {
            loginResult =  loginService.memberLoginByPwd(account,pwd,loginIp);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
            return AnyoujiaResult.build(500, "系统异常");
        }

        if (loginResult instanceof Null) {
            return AnyoujiaResult.build(400, "账号不存在");
        }
        if (loginResult != null) {
            return AnyoujiaResult.ok(loginResult);
        }
        return AnyoujiaResult.build(400,"登录小程序失败");
    }


}
