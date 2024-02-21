package cn.esctasy.qqchat.bot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "cn.esctasy.qqchat.bot.event")
public class BotAutoConfiguration {
}
