package cn.esctasy.qqchat.core.chain.impl.request;

import cn.esctasy.qqchat.common.ws.WsExample;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.bean.escalation.request.FriendEs;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FriendHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"friend".equals(code)) {
            this.goNext(code, metadata, "FriendHandle");
            return;
        }

        FriendEs friendEs = JSON.parseObject(metadata, FriendEs.class);

        Map<String, Object> param = new HashMap<>();
        param.put("flag", friendEs.getFlag());
        param.put("approve", true);
        WsExample.getWs().send(Reply.build("set_friend_add_request", param, "test1"));
    }
}
