package cn.esctasy.qqchat.core.bean.escalation.notice;

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
}
