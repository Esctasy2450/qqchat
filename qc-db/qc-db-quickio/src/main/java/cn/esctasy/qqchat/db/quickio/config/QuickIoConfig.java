package cn.esctasy.qqchat.db.quickio.config;

import cn.hutool.extra.spring.SpringUtil;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @ConfigurationProperties(prefix = "esctasy.qqchat.quick-io")
public class QuickIoConfig {

    private static final DB db = QuickIO.usingDB(Config.of(c -> {
        c.name("qqchat");
        c.path("data/qio");                 // Custom base path.
        c.cache(16L * 1024 * 1024);         // Set cache size.
    }));

    private static String name = "qqchat";
    private static String path = "data/qio";
    private static Long cacheSize = 16L * 1024 * 1024;

    public static DB getDB() {
        return db;
    }
}
