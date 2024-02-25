package cn.esctasy.qqchat.bot.event.message.chat.third.bard;

import com.pkslow.ai.GoogleBardClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleBardConfig {

    @Bean
    public GoogleBardClient googleBardClient(@Value("${esctasy.qqchat.third-chat.bard.token}") String token) {
        return new GoogleBardClient(token);
    }

    public String getMsg(String msg) {
        return "";
    }
}
