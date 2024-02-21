package cn.esctasy.qqchat.core.event.chain.impl.notice;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;

public class NotifyHandle extends Handle {
    @Override
    public void handling(String metadata) {
        this.goChild(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtNtNotify();
    }
}
