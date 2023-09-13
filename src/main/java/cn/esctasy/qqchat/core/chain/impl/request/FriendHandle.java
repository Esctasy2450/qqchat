package cn.esctasy.qqchat.core.chain.impl.request;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
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
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtRtFriend())) {
            this.goNext(metadata);
            return;
        }

        FriendEs friendEs = JSON.parseObject(metadata, FriendEs.class);

        Map<String, Object> param = new HashMap<>();
        param.put("flag", friendEs.getFlag());
        param.put("approve", true);
        Reply.build("set_friend_add_request", param, "test1").send();
    }
}
