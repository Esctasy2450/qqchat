package cn.esctasy.qqchat.core.chain.impl.notice.notify;

import cn.esctasy.qqchat.common.local.LocalInfo;
import cn.esctasy.qqchat.core.bean.escalation.notice.notify.PokeEs;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;

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

        if (0 == pokeEs.getSender_id() || 0 == pokeEs.getTarget_id()) {
            //数据有问题不处理
            return;
        }

        if (pokeEs.getTarget_id() != LocalInfo.get().getSelf_id() || pokeEs.getTarget_id() == pokeEs.getSender_id()) {
            //排除目标不是本机的戳一戳，和本机戳本机的戳一戳
            return;
        }

        Map<String, Object> map = new HashMap<>();
        if (0 != pokeEs.getGroup_id()) {
            map.put("group_id", pokeEs.getGroup_id());
            map.put("message", "[CQ:poke,qq=" + pokeEs.getSender_id() + "]");
            map.put("auto_escape", false);
            Reply.build("send_group_msg", map).send();
        } else {
            map.put("user_id", pokeEs.getUser_id());
            map.put("message", "[CQ:shake]");
            map.put("auto_escape", false);
            Reply.build("send_private_msg", map).send();
        }
    }
}
