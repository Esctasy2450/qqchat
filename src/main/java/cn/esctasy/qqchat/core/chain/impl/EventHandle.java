package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.chain.Handle;

/**
 * 事件责任链
 */
public class EventHandle extends Handle {
    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtEvent())) {
            this.goNext(metadata);
            return;
        }

        this.goChild(metadata);
    }
}
