package cn.esctasy.qqchat.core.event.chain.impl.message;

import cn.esctasy.qqchat.api.event.handle.message.GroupEvent;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.hutool.extra.spring.SpringUtil;

/**
 * 消息
 * 群聊
 * */
public class GroupHandle extends Handle {

    @Override
    public void handling(String metadata) {
        SpringUtil.getBean(GroupEvent.class).eventHandle(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtMtGroup();
    }
}
