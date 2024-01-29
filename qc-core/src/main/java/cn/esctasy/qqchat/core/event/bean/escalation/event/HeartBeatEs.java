package cn.esctasy.qqchat.core.event.bean.escalation.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class HeartBeatEs extends EventEs {
    /**
     * 应用程序状态
     */
    @JSONField(name = "status")
    private HeartBeatStatus status;
    /**
     * 距离上一次心跳包的时间(单位是毫秒)
     */
    @JSONField(name = "interval")
    private Integer interval;

    /**
     * 心跳状态信息
     */
    @NoArgsConstructor
    @Data
    public static class HeartBeatStatus {
        /**
         * 程序是否可用
         */
        @JSONField(name = "app_enabled")
        private Boolean appEnabled;
        /**
         * 插件正常(可能为 null)
         */
        @JSONField(name = "app_good")
        private Boolean appGood;
        /**
         * 程序是否初始化完毕
         */
        @JSONField(name = "app_initialized")
        private Boolean appInitialized;
        /**
         * good
         */
        @JSONField(name = "good")
        private Boolean good;
        /**
         * 是否在线
         */
        @JSONField(name = "online")
        private Boolean online;
        /**
         * pluginsGood
         */
        @JSONField(name = "plugins_good")
        private Object pluginsGood;
        /**
         * 统计信息
         */
        @JSONField(name = "stat")
        private StatDTO stat;

        /**
         * StatDTO
         */
        @NoArgsConstructor
        @Data
        public static class StatDTO {
            /**
             * 收包数
             */
            @JSONField(name = "packet_received")
            private Integer packetReceived;
            /**
             * 发包数
             */
            @JSONField(name = "packet_sent")
            private Integer packetSent;
            /**
             * 丢包数
             */
            @JSONField(name = "packet_lost")
            private Integer packetLost;
            /**
             * 消息接收数
             */
            @JSONField(name = "message_received")
            private Integer messageReceived;
            /**
             * 消息发送数
             */
            @JSONField(name = "message_sent")
            private Integer messageSent;
            /**
             * 连接断开次数
             */
            @JSONField(name = "disconnect_times")
            private Integer disconnectTimes;
            /**
             * 连接丢失次数
             */
            @JSONField(name = "lost_times")
            private Integer lostTimes;
            /**
             * 最后一次消息时间
             */
            @JSONField(name = "last_message_time")
            private Integer lastMessageTime;
        }
    }
}
