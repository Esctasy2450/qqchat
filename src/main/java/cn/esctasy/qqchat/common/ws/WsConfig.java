package cn.esctasy.qqchat.common.ws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * socket配置
 */
@Component
@ConfigurationProperties(prefix = WsConfig.prefix)
public class WsConfig {
    public static final String prefix = "esctasy.qqchat.ws";

    /**
     * cqhttp地址
     */
    @Getter
    @Setter
    private String socketPath;
}
