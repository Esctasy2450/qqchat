package cn.esctasy.qqchat.core.config;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("WsConfig")
@ConfigurationProperties(WsConfig.prefix)
public class WsConfig {
    static final String prefix = "esctasy.qqchat.ws";

    /**
     * socket地址
     */
    private String socketPath = "ws://localhost:5700";

    private Retry retry;

    @Getter
    @Setter
    public static class Retry {
        /**
         * 是否启用重试
         */
        private boolean enable = true;

        /**
         * 重试时间间隔
         */
        private int interval = 3000;

        /**
         * 最大重试次数
         */
        private int max = 5;

        @Setter(AccessLevel.NONE)
        private int retry = 0;

        public void resetting() {
            this.retry = 0;
        }

        public void add() {
            this.retry++;
        }
    }
}
