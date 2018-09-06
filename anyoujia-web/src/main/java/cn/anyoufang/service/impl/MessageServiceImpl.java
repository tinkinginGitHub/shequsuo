package cn.anyoufang.service.impl;

import cn.anyoufang.entity.SpMemberWx;
import cn.anyoufang.entity.SpMemberWxExample;
import cn.anyoufang.mapper.SpMemberWxMapper;
import cn.anyoufang.message.resp.TextMessage;
import cn.anyoufang.service.MessageService;
import cn.anyoufang.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SpMemberWxMapper wxMapper;


    @Override
    public String handleTypesOfMessageFromWx(Map<String, String> requestMap) {

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
            respMessage="";
        }
        // 用户发来的是图片消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            respMessage="";
        }
        // 用户发来地理位置信息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            respMessage="";
        }
        // 用户发来链接消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            respMessage="";
        }
        // 用户发来音频消息
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            respMessage="";
        }
        /* 事件推送的处理 */
        else if (messageType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            /* 事件类型 */
            String eventType = requestMap.get("Event");
            /* 订阅 */
            if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                respMessage="欢迎关注安优家智慧社区公众号!\n";
                StringBuffer contentMsg = new StringBuffer();
                contentMsg.append("您还可以回复下列数字，体验相应服务").append("\n\n");
                contentMsg.append("1  我就是个测试的").append("\n");
                contentMsg.append("2  我木有").append("\n");
                contentMsg.append("3  我是多图文").append("\n");
                respMessage = respMessage+contentMsg.toString();
                SpMemberWx user = new SpMemberWx();
                user.setSubscribe(true);
                wxMapper.updateByExampleSelective(user,getWxExample(fromUserName));
            }
            /* 取消订阅 */
            else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                SpMemberWx user = new SpMemberWx();
                user.setSubscribe(false);
                wxMapper.updateByExampleSelective(user,getWxExample(fromUserName));
            }
            // 点击按钮事件
            else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                respMessage="";
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
