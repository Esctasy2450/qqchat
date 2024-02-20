package cn.esctasy.qqchat.bot.event.request;

import cn.esctasy.qqchat.api.event.bean.request.FriendEs;
import cn.esctasy.qqchat.api.event.handle.request.FriendEvent;
import cn.esctasy.qqchat.api.reply.bean.Reply;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class FriendEventImpl implements FriendEvent {
    @Override
    public void eventHandle(String metadata) {
        FriendEs friendEs = JSON.parseObject(metadata, FriendEs.class);

        Map<String, Object> param = new HashMap<>();
        param.put("flag", friendEs.getFlag());
        param.put("approve", true);
        Reply.build("set_friend_add_request", param, "test1").send();
    }
}
