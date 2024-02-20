package cn.esctasy.qqchat.core.event.chain.impl.request;

import cn.esctasy.qqchat.api.event.handle.request.FriendEvent;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendHandle extends Handle {

    @Override
    public void handling(String metadata) {
        SpringUtil.getBean(FriendEvent.class).eventHandle(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtRtFriend();
    }
}
