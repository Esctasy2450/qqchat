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
        this.goChild(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtMessage();
    }
}
