package cn.esctasy.qqchat.core.chain.impl.event;

import cn.esctasy.qqchat.common.local.LocalInfo;
import cn.esctasy.qqchat.core.bean.escalation.event.LifeCycleEs;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 生命周期事件
 * */
@Slf4j
public class LifeCycle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"lifecycle".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        LifeCycleEs lifeCycleEs = JSON.parseObject(metadata, LifeCycleEs.class);
        LocalInfo.get().setSelf_id(lifeCycleEs.getSelf_id());
        log.info("lifecycle, {}", metadata);
    }
}
