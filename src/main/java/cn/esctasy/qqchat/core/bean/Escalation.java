package cn.esctasy.qqchat.core.bean;

import lombok.Data;

/**
 * socket上报信息
 */
@Data
public class Escalation {

    /**
     * 表示该上报的类型
     * message 消息
     * message_sent 消息发送
     * request 请求
     * notice 通知
     * meta_event 元事件
     */
    private String post_type;

    /**
     * 收到事件的机器人的QQ号
     */
    private int self_id;

    /**
     * 事件发生的unix时间戳
     */
    private int time;
}
