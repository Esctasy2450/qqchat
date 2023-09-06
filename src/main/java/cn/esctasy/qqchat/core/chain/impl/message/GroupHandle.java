package cn.esctasy.qqchat.core.chain.impl.message;

import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.core.bean.escalation.message.GroupEs;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;
import org.java_websocket.client.WebSocketClient;

import java.util.HashMap;
import java.util.Map;

public class GroupHandle extends Handle {
    @Override
    public void handling(String code, String metadata) {
        if (!"group".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        GroupEs groupEs = JSON.parseObject(metadata, GroupEs.class);

        Map<String, Object> param = new HashMap<>();
        param.put("group_id", groupEs.getGroup_id());
        param.put("message", groupEs.getRaw_message());
        param.put("auto_escape", true);
        Reply.build("send_group_msg", param, "test").send();
    }
}
