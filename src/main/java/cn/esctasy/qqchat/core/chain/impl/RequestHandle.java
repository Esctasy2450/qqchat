package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.chain.Handle;

/**
 * 请求上报事件
 */
public class RequestHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtRequest())) {
            this.goNext(metadata);
            return;
        }

        this.goChild(metadata);
    }
}
