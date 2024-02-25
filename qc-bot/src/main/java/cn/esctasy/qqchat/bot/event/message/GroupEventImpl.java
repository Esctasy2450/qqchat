package cn.esctasy.qqchat.bot.event.message;

import cn.esctasy.qqchat.api.event.bean.message.GroupEs;
import cn.esctasy.qqchat.api.event.handle.message.GroupEvent;
import cn.esctasy.qqchat.api.reply.bean.Reply;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GroupEventImpl implements GroupEvent {
    @Override
    public void eventHandle(String metadata) {
        GroupEs groupEs = JSON.parseObject(metadata, GroupEs.class);

        Map<String, Object> param = new HashMap<>();
        param.put("group_id", groupEs.getGroup_id());
        param.put("message", groupEs.getRaw_message());
        param.put("auto_escape", true);
        Reply.build("send_group_msg", param, "test").send();
    }
}
