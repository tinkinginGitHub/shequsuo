package cn.anyoufang.controller;

import cn.anyoufang.entity.WeiXinVO;
import cn.anyoufang.service.WxUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserOpenController.class);

    @Autowired
    private WxUserService wxUserService;


    //获取用户的openid
    @SuppressWarnings({ "resource", "deprecation" })
    @RequestMapping("/getCode")
    public ModelAndView getCode(HttpServletRequest request, String code,
            String myUrl) {

        WeiXinVO weiXinVO = null;
        try {
            weiXinVO = wxUserService.getAndSaveUserInfoFromWx(code,myUrl);
        } catch (IOException e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        HttpSession session =  request.getSession();
        session.setAttribute("weiXinVO", weiXinVO);
        ModelAndView myIndex = new ModelAndView(new RedirectView(myUrl));
        return myIndex;
    }
}