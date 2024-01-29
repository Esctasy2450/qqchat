package cn.esctasy.qqchat.core.event.chain.impl;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息责任链
 */
@Slf4j
public class MessageHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtMessage())) {
            this.goNext(metadata);
            return;
        }

        this.goChild(metadata);
    }
}
