package cn.esctasy.qqchat.db.quickio.config;

import com.github.artbits.quickio.core.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "esctasy.qqchat.quick-io")
public class QuickIoConfig {
    private String name = "qqchat";
    private String path = "data/qio";
    private Long cacheSize = 16L * 1024 * 1024;
    
    public Config getConfig() {
        return Config.of(c -> {
            c.name("qqchat");
            c.path("data/qio");                 // Custom base path.
            c.cache(16L * 1024 * 1024);         // Set cache size.
        });
    }
}
