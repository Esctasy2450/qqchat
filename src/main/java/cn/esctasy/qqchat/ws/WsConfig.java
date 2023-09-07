package cn.esctasy.qqchat.ws;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("WsConfig")
@ConfigurationProperties(WsConfig.prefix)
public class WsConfig {
    public static final String prefix = "esctasy.qqchat.ws";

    private String socketPath;

    private Retry retry;

    @Getter
    @Setter
    public static class Retry {
        private int max;
        private int interval;
        private boolean enable;

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
