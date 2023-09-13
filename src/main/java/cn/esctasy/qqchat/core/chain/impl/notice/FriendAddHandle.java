package cn.esctasy.qqchat.core.chain.impl.notice;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.chain.Handle;
import lombok.extern.slf4j.Slf4j;

/**
 * 提醒
 * 加好友
 * */
@Slf4j
public class FriendAddHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtNtFriendAdd())) {
            this.goNext(metadata);
            return;
        }

        log.info("加好友成功");
    }
}
