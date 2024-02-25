package cn.esctasy.qqchat.core.event.chain.impl.event;

import cn.esctasy.qqchat.api.event.handle.event.HeartEvent;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 心跳事件
 */
@Slf4j
public class HeartHandle extends Handle {

    @Override
    public void handling(String metadata) {
        SpringUtil.getBean(HeartEvent.class).eventHandle(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtEtHeartBeat();
    }
}
