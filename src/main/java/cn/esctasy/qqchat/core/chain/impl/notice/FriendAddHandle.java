package cn.esctasy.qqchat.core.chain.impl.notice;

import cn.esctasy.qqchat.core.chain.Handle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendAddHandle extends Handle {
    @Override
    public void handling(String code, String metadata) {
        if (!"friend_add".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        log.info("加好友成功");
    }
}
