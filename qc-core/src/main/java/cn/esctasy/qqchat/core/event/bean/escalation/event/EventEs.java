package cn.esctasy.qqchat.core.event.bean.escalation.event;

import cn.esctasy.qqchat.core.event.bean.escalation.Escalation;
import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "meta_event_type")
    private String metaEventType;
}
