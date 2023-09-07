package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.event.HeartHandle;
import cn.esctasy.qqchat.core.chain.impl.event.LifeCycle;
import cn.esctasy.qqchat.core.bean.escalation.event.EventEs;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 事件责任链
 */
public class EventHandle extends Handle {
    private final Handle heart = new HeartHandle(new LifeCycle(null, null), null);
    private final Handle lifeCycle = new LifeCycle(null, null);

    {
        heart.setNext(lifeCycle);
    }



    @Override
    public void handling(String code, String metadata) {
        if (!"meta_event".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        EventEs eventEs = JSON.parseObject(metadata, EventEs.class);
        heart.handling(eventEs.getMeta_event_type(), metadata);
    }
}
