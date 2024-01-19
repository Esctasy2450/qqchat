package cn.esctasy.qqchat.core.bean.escalation.event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class HeartBeatEs extends EventEs {
    /**
     * status
     */
    @JSONField(name = "status")
    private StatusDTO status;
    /**
     * interval
     */
    @JSONField(name = "interval")
    private Integer interval;

    /**
     * StatusDTO
     */
    @NoArgsConstructor
    @Data
    public static class StatusDTO {
        /**
         * appEnabled
         */
        @JSONField(name = "app_enabled")
        private Boolean appEnabled;
        /**
         * appGood
         */
        @JSONField(name = "app_good")
        private Boolean appGood;
        /**
         * appInitialized
         */
        @JSONField(name = "app_initialized")
        private Boolean appInitialized;
        /**
         * good
         */
        @JSONField(name = "good")
        private Boolean good;
        /**
         * online
         */
        @JSONField(name = "online")
        private Boolean online;
        /**
         * pluginsGood
         */
        @JSONField(name = "plugins_good")
        private Object pluginsGood;
        /**
         * stat
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
             * packetReceived
             */
            @JSONField(name = "packet_received")
            private Integer packetReceived;
            /**
             * packetSent
             */
            @JSONField(name = "packet_sent")
            private Integer packetSent;
            /**
             * packetLost
             */
            @JSONField(name = "packet_lost")
            private Integer packetLost;
            /**
             * messageReceived
             */
            @JSONField(name = "message_received")
            private Integer messageReceived;
            /**
             * messageSent
             */
            @JSONField(name = "message_sent")
            private Integer messageSent;
            /**
             * disconnectTimes
             */
            @JSONField(name = "disconnect_times")
            private Integer disconnectTimes;
            /**
             * lostTimes
             */
            @JSONField(name = "lost_times")
            private Integer lostTimes;
            /**
             * lastMessageTime
             */
            @JSONField(name = "last_message_time")
            private Integer lastMessageTime;
        }
    }
}
