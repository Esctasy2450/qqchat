package cn.esctasy.qqchat.core.event.chain.impl.message;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.core.event.bean.escalation.message.PrivateEs;
import cn.esctasy.qqchat.core.event.bean.reply.Reply;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.esctasy.qqchat.core.event.chain.impl.message.chat.menu.BaseMenu;
import cn.esctasy.qqchat.core.event.chain.impl.message.chat.third.ThirdChat;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 * 私聊
 */
public class PrivateHandle extends Handle {

    @Override
    public void handling(String metadata) {
        PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);

        String message = getMsg(privateEs.getRawMessage());

        sendMsg(privateEs, message);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtMtPrivate();
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
            return SpringContextHolder.getBean(ThirdChat.class).getMsg(message);
        }
    }

    private String parseShall(String msg) {
        String[] strs = StringUtils.tokenizeToStringArray(msg, "\t");
        return BaseMenu.parse(strs[0]);
    }
}
