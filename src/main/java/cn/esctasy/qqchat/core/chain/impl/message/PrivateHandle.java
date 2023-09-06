package cn.esctasy.qqchat.core.chain.impl.message;

import cn.esctasy.qqchat.core.chain.impl.message.chat.third.ThirdChat;
import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.bean.escalation.message.PrivateEs;
import cn.esctasy.qqchat.core.chain.impl.message.chat.menu.BaseMenu;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PrivateHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"private".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);

        String message = getMsg(privateEs.getRaw_message());

        sendMsg(privateEs, message);
    }

    /**
     * 发送消息
     */
    private void sendMsg(PrivateEs privateEs, String message) {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", privateEs.getUser_id());
        param.put("message", message);
        param.put("auto_escape", true);
        Reply.build("send_private_msg", param).send();
    }

    private String getMsg(String message) {
        if (message.startsWith("#")) {
            return parseShall(message.substring(1));
        } else {
            return SpringContextHolder.getBean(ThirdChat.class).getMsg(message);
        }
    }

    private String parseShall(String msg) {
        String[] strs = StringUtils.tokenizeToStringArray(msg, "\t");
        return BaseMenu.parse(strs[0]);
    }
}
