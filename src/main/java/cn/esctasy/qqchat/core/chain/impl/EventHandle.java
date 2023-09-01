package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.event.HeartHandle;
import cn.esctasy.qqchat.core.chain.impl.event.LifeCycle;
import cn.esctasy.qqchat.core.bean.event.EventEs;
import com.alibaba.fastjson.JSON;

/**
 * 事件责任链
 */
public class EventHandle extends Handle {
    Handle heart = new HeartHandle();
    Handle lifeCycle = new LifeCycle();

    {
        heart.setNext(lifeCycle);
    }

    @Override
    public void handling(String code, String metadata) {
        if ("meta_event".equals(code)) {
            EventEs eventEs = JSON.parseObject(metadata, EventEs.class);
            heart.handling(eventEs.getMeta_event_type(), metadata);
            return;
        }

        this.goNext(code, metadata, "EventHandle");
    }
}
