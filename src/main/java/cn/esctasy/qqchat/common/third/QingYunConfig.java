package cn.esctasy.qqchat.common.third;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 青云客聊天api
 */
@Component
@ConfigurationProperties(prefix = QingYunConfig.prefix)
public class QingYunConfig {
    public static final String prefix = "esctasy.qqchat.qingyun";

    /**
     * 地址
     */
    @Getter
    @Setter
    private String api;
}
