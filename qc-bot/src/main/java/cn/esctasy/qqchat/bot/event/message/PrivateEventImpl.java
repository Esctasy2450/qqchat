package cn.esctasy.qqchat.bot.event.message;

import cn.esctasy.qqchat.api.event.bean.message.PrivateEs;
import cn.esctasy.qqchat.api.event.handle.message.PrivateEvent;
import cn.esctasy.qqchat.api.reply.bean.Reply;
import cn.esctasy.qqchat.bot.event.message.chat.menu.BaseMenu;
import cn.esctasy.qqchat.bot.event.message.chat.third.ThirdChat;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class PrivateEventImpl implements PrivateEvent {
    @Override
    public void eventHandle(String metadata) {
        PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);

        String message = getMsg(privateEs.getRawMessage());

        sendMsg(privateEs, message);
    }

    /**
     * 发送消息
     */
    private void sendMsg(PrivateEs privateEs, String message) {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", privateEs.getUserId());
        param.put("message", message);
        param.put("auto_escape", true);
        Reply.build("send_private_msg", param).send();
    }

    private String getMsg(String message) {
        if (message.startsWith("#")) {
            return parseShall(message.substring(1));
        } else if (message.startsWith("[CQ:")) {
            return "cq消息";
        } else {
            return SpringUtil.getBean(ThirdChat.class).getMsg(message);
        }
    }

    private String parseShall(String msg) {
        String[] strs = StringUtils.tokenizeToStringArray(msg, "\t");
        return BaseMenu.parse(strs[0]);
    }
}
