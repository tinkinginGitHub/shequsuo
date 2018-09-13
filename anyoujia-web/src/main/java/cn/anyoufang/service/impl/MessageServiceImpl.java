package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpMemberWx;
import cn.anyoufang.entity.SpMemberWxExample;
import cn.anyoufang.enums.WxConstant;
import cn.anyoufang.mapper.SpMemberWxMapper;
import cn.anyoufang.message.resp.TextMessage;
import cn.anyoufang.service.MessageService;
import cn.anyoufang.service.WxUserService;
import cn.anyoufang.util.MessageUtil;
import cn.anyoufang.util.WeixinUtil;
import cn.anyoufang.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Autowired
    private SpMemberWxMapper wxMapper;

    @Autowired
    private WxUserService userService;


    @Override
    public String handleTypesOfMessageFromWx(Map<String, String> requestMap) throws Exception {

        String respMessage = "";

        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");

        // 公众帐号
        String toUserName = requestMap.get("ToUserName");
        // 消息类型
        String msgType = requestMap.get("MsgType");

        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        // 获得用户发来的消息类型，并做相应的处理
        String messageType = requestMap.get("MsgType");

        // 进行消息分发
        // 用户发来的是文本消息
        if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            respMessage="您发的是文本信息";
        }
        // 用户发来的是图片消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            respMessage="您发的是图片信息";
        }
        // 用户发来地理位置信息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            respMessage="您发的是一条地理位置信息";
        }
        // 用户发来链接消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            respMessage="您发的是一条连接信息";
        }
        // 用户发来音频消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            respMessage="您发的是语音信息";
        }
        /* 事件推送的处理 */
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            /* 事件类型 */
            String eventType = requestMap.get("Event");
            /* 订阅 */
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                StringBuffer contentMsg = new StringBuffer();
                SpMemberWxExample example = new SpMemberWxExample();
                SpMemberWxExample.Criteria criteria = example.createCriteria();
                criteria.andOpenidEqualTo(fromUserName);
                List<SpMemberWx> list = wxMapper.selectByExample(example);
                if(list ==null || list.size() == 0) {
                    respMessage="欢迎关注安优家智慧社区公众号!\n";
                    String accessToken = RedisUtils.get(WxConstant.ACCESS_TOKEN.getValue());
                    if(accessToken ==null) {
                        accessToken = WeixinUtil.getAccessToken();
                    }
                    userService.saveWxUserBasicInfo(accessToken,fromUserName);
                }else {
                    respMessage="你回来了真好，不然总担心你会错过我，么么哒!";
                    SpMemberWx user = new SpMemberWx();
                    user.setSubscribe(true);
                    wxMapper.updateByExampleSelective(user,getWxExample(fromUserName));
                }
            }
            /* 取消订阅 */
            else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                SpMemberWx user = new SpMemberWx();
                user.setSubscribe(false);
                wxMapper.updateByExampleSelective(user,getWxExample(fromUserName));
            }
            // 点击按钮事件
            else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                respMessage="这是一个测试菜单的点击事件";
            }
        }
        textMessage.setContent(respMessage);
        return MessageUtil.messageToXml(textMessage);
    }

    /**
     * 根据openId查询用户微信信息
     * @param openId
     * @return
     */
    private SpMemberWxExample getWxExample(String openId) {
        SpMemberWxExample  example = new SpMemberWxExample();
        SpMemberWxExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openId);
        return example;
    }
}
