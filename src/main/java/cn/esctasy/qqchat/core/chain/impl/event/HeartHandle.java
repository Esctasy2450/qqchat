package cn.esctasy.qqchat.core.chain.impl.event;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.chain.Handle;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 心跳事件
 * */
@Slf4j
public class HeartHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtEtHeartBeat())) {
            this.goNext(metadata);
            return;
        }

        log.info("heart beat success");
    }
}
