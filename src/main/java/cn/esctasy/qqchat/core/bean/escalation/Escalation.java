package cn.esctasy.qqchat.core.bean.escalation;

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

    /**
     * 状态，调用的返回值，上报数据没有此字段，故根据此字段判断是 响应 还是 上报
     * 响应：调用时返回值，此字段存在值
     * 上报：qq接收到消息时自动上报，无此字段
     */
    private String echo;
}
