package cn.esctasy.qqchat.core.chain.impl.event;

import cn.esctasy.qqchat.core.chain.Handle;
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
            this.goNext(code, metadata, "LifeCycle");
            return;
        }

        log.info("lifecycle");
    }
}
