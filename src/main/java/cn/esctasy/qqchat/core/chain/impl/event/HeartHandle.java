package cn.esctasy.qqchat.core.chain.impl.event;

import cn.esctasy.qqchat.core.chain.Handle;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 心跳事件
 * */
@Slf4j
public class HeartHandle extends Handle {
    @Override
    public void handling(String code, String metadata) {
        if (!"heartbeat".equals(code)) {
            this.goNext(code, metadata, "HeartHandle");
            return;
        }

        log.info("heart beat success");
    }
}
