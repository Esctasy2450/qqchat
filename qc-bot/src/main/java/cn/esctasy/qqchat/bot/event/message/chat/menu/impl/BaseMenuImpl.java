package cn.esctasy.qqchat.bot.event.message.chat.menu.impl;

import cn.esctasy.qqchat.bot.event.message.chat.menu.MenuInterface;

public class BaseMenuImpl implements MenuInterface {
    @Override
    public String getRecv() {
        return "1. 当前时间\n2. 测试2\n3. 测试3";
    }
}
