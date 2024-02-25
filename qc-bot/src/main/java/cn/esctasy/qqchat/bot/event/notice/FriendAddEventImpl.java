package cn.esctasy.qqchat.bot.event.notice;

import cn.esctasy.qqchat.api.event.handle.notice.FriendAddEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FriendAddEventImpl implements FriendAddEvent {
    @Override
    public void eventHandle(String metadata) {
        log.info("加好友成功");
    }
}
