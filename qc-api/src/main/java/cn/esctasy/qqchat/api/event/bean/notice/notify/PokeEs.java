package cn.esctasy.qqchat.api.event.bean.notice.notify;

import cn.esctasy.qqchat.api.event.bean.notice.NoticeEs;
import lombok.Getter;
import lombok.Setter;

/**
 * 戳一戳事件
 */
@Getter
@Setter
public class PokeEs extends NoticeEs {
    private int sender_id;
    private int group_id;
    private int user_id;
    private int target_id;
}
