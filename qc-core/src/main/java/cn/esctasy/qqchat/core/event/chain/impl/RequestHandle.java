package cn.esctasy.qqchat.core.event.chain.impl;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;

/**
 * 请求上报事件
 */
public class RequestHandle extends Handle {

    @Override
    public void handling(String metadata) {
        this.goChild(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtRequest();
    }
}
