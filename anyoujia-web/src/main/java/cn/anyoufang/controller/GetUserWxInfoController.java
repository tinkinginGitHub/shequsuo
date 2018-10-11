package cn.anyoufang.controller;

import cn.anyoufang.entity.AnyoujiaResult;
import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.service.WxUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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


    /**
     *  获取用户的openid
     */
    @SuppressWarnings({ "resource", "deprecation" })
    @RequestMapping("/getCode")
    @ApiOperation(value = "获取用户微信信息并保存",httpMethod = "POST",response = ModelAndView.class)
    public AnyoujiaResult getCode(@RequestParam String code,
                                  @RequestParam String nickname,
                                  @RequestParam String headurl,
                                  @RequestParam String sex) {

        WeiXinVO weiXinVO = null;
        try {
            weiXinVO = wxUserService.getAndSaveUserInfoFromWx(code,nickname,headurl,sex);
        } catch (IOException e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        if(weiXinVO != null) {
            return AnyoujiaResult.ok();
        }
        return AnyoujiaResult.build(400,"登录小程序失败");
    }
}
