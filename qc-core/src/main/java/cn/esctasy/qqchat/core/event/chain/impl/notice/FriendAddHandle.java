package cn.esctasy.qqchat.core.event.chain.impl.notice;

import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import lombok.extern.slf4j.Slf4j;

/**
 * 提醒
 * 加好友
 * */
@Slf4j
public class FriendAddHandle extends Handle {

    @Override
    public void handling(String metadata) {
        log.info("加好友成功");
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtNtFriendAdd();
    }
}
