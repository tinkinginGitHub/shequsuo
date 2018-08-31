package cn.anyoufang.controller;

import cn.anyoufang.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class WxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

    /**
     * 获取前台调用微信jsapi接口相关的参数如签名等
     * @param url
     * @return
     */
    @RequestMapping("/sign")
    public Map get(@RequestParam String url) {
        return WeixinUtil.sign(url);
    }
}
