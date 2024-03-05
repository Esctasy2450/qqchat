package cn.esctasy.qqchat.bot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类，引入bot模块的同时自动装配
 * */
@Configuration
@ComponentScan(value = "cn.esctasy.qqchat.bot")
public class BotAutoConfiguration {
}
