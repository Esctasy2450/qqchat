package cn.esctasy.qqchat.core.chain.impl.notice;

import cn.esctasy.qqchat.core.bean.escalation.notice.PokeEs;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 提醒
 * 戳一戳
 */
public class PokeHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"poke".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        PokeEs pokeEs = JSON.parseObject(metadata, PokeEs.class);

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", pokeEs.getSender_id());
        map.put("message", "[CQ:shake]");
        map.put("auto_escape", false);
        Reply.build("", map).send();
    }
}
