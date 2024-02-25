package cn.esctasy.qqchat.bot.event.notice.notify;

import cn.esctasy.qqchat.api.event.bean.notice.notify.PokeEs;
import cn.esctasy.qqchat.api.event.handle.notice.notify.PokeEvent;
import cn.esctasy.qqchat.api.local.LocalInfo;
import cn.esctasy.qqchat.api.reply.bean.Reply;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PokeEventImpl implements PokeEvent {
    @Override
    public void eventHandle(String metadata) {
        PokeEs pokeEs = JSON.parseObject(metadata, PokeEs.class);

        if (0 == pokeEs.getSender_id() || 0 == pokeEs.getTarget_id()) {
            //数据有问题不处理
            return;
        }

        if (pokeEs.getTarget_id() != LocalInfo.getSelf_id() || pokeEs.getTarget_id() == pokeEs.getSender_id()) {
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
