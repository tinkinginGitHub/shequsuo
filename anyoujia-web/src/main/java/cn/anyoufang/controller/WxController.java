package cn.anyoufang.controller;

import cn.anyoufang.util.WeixinUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WxController {


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
