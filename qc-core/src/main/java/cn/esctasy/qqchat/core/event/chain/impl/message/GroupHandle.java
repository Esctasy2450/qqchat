package cn.esctasy.qqchat.core.event.chain.impl.message;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.bean.escalation.message.GroupEs;
import cn.esctasy.qqchat.core.event.bean.reply.Reply;
import cn.esctasy.qqchat.core.event.chain.Handle;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 * 群聊
 * */
public class GroupHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtMtGroup())) {
            this.goNext(metadata);
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
