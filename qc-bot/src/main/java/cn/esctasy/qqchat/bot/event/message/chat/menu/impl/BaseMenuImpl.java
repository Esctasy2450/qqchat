package cn.esctasy.qqchat.bot.event.message.chat.menu.impl;

import cn.esctasy.qqchat.bot.event.message.chat.menu.MenuInterface;

public class BaseMenuImpl implements MenuInterface {
    @Override
    public String getRecv() {
        return "1. 签到系统\n2.商城系统\n3. 历练系统\n4. 寻宝系统";
    }
}
