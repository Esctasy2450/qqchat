package cn.esctasy.qqchat.bot.config;

import cn.esctasy.qqchat.bot.event.request.FriendEventImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotAutoConfiguration {

    @Bean
    @ConditionalOnClass(FriendEventImpl.class)
    @ConditionalOnMissingBean(FriendEventImpl.class)
    public FriendEventImpl getFriendEvent() {
        return new FriendEventImpl();
    }
}
