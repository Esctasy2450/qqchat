package cn.esctasy.qqchat.core.chain.impl.message.chat.third.bard;

import com.pkslow.ai.GoogleBardClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleBardConfig {
    GoogleBardClient client;

    @Bean
    public GoogleBardClient googleBardClient(@Value("${esctasy.qqchat.third-chat.bard.token}") String token) {
        this.client = new GoogleBardClient(token);
        return this.client;
    }

    public String getMsg(String msg) {
        return client.ask(msg).getChosenAnswer();
    }
}
