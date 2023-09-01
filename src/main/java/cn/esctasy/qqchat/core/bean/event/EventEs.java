package cn.esctasy.qqchat.core.bean.event;

import cn.esctasy.qqchat.core.bean.Escalation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 元事件上报
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EventEs extends Escalation {

    /**
     * 事件类型
     * lifecycle 生命周期开始
     * heartbeat 心跳
     */
    private String meta_event_type;
}
