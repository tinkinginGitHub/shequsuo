package cn.anyoufang.controller;

import cn.anyoufang.service.MessageService;
import cn.anyoufang.util.AesUtils;
import cn.anyoufang.util.MessageUtil;
import cn.anyoufang.util.SignUtil;
import cn.anyoufang.util.WeixinUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author daiping
 * @date 2018-09-05
 */
@Api(value = "Wx",description = "处理微信jsapi签名，以及微信公众号消息的发送")
@RestController
@RequestMapping("/api")
public class WxController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private MessageService messageService;

    /**
     * 获取前台调用微信jsapi接口相关的参数如签名等
     *
     * @param url
     * @return
     */
    @RequestMapping("/sign")
    @ApiOperation(value = "获取前台调用微信jsapi接口的相关参数",httpMethod = "GET",response = Map.class)
    public Map get(@RequestParam String url) {
        try {
            return WeixinUtil.sign(url);
        } catch (NoSuchAlgorithmException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        } catch (UnsupportedEncodingException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException 用来和微信服务器之间相互认证
     */
    @RequestMapping(method = RequestMethod.GET, value = "/wxsign")
    public void handleWxGet(HttpServletRequest request, HttpServletResponse response) {
        /*
         * 下面四个参数都是微信服务器发送过来的，
         * 其中signature、timestamp、nonce是要参与服务器的验证的，
         * 而echostr是在我们通过验证后返回给服务器告诉服务器我们就是要通讯的那个远程服务器
         */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        LOGGER.info("微信token" + echostr);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        //在SignUtil中使用checkSignature来进行验证
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            //验证通过后就把echostr返回给微信服务器。
            out.print(echostr);
        }
        out.close();
        out = null;
    }


    /**
     * 用来和微信服务器通信
     */
    @RequestMapping(method = RequestMethod.POST, value = "/wxsign")
    public void handleWxPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        PrintWriter out = null;
        try {
            // 从请求中获得xml格式的信息。并转化为map类型
            Map<String, String> requestMapSecret = MessageUtil.parseXml(request);

            // 获得解密后的消息正文
            String mingwenXML = AesUtils.descMessage(
                    requestMapSecret.get("Encrypt"),
                    request.getParameter("msg_signature"),
                    timestamp,
                    nonce);

            //将明文的xml再次解析后放入map中，
            Map<String, String> requestMap = MessageUtil
                    .parseXml(new StringReader(mingwenXML));

            String respMessage = messageService.handleTypesOfMessageFromWx(requestMap);
            // 给返回的消息加密
            AesUtils.aescMessage(respMessage, timestamp, nonce);

            //返回消息给微信服务器，然后微信服务器把消息转发给用户
            out = response.getWriter();
            out.print(respMessage);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        } finally {
            if (out != null) {
                out.close();
            }

        }
    }


}
