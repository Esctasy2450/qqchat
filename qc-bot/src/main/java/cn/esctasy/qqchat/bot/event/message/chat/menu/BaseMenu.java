package cn.esctasy.qqchat.bot.event.message.chat.menu;

import cn.esctasy.qqchat.api.event.bean.message.PrivateEs;
import cn.esctasy.qqchat.bot.event.message.chat.menu.impl.BaseMenuImpl;
import cn.esctasy.qqchat.bot.event.message.chat.menu.impl.RegisterImpl;
import cn.esctasy.qqchat.bot.event.message.chat.menu.impl.SignInMenuImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseMenu {
    BASE("菜单", new BaseMenuImpl()),
    REGISTER("注册", new RegisterImpl()),
    SIGN_IN("签到系统", new SignInMenuImpl());

    private final String label;
    private final MenuInterface impl;

    public static String parse(PrivateEs privateEs) {
        for (BaseMenu baseMenu : values()) {
            if (baseMenu.getLabel().equals(privateEs.getRawMessage().substring(1))) {
                //todo 每次在执行对应的命令之前，读数据库看是否存在用户，不存在则创建一个
                return baseMenu.getImpl().getRecv();
            }
        }

        return privateEs.getRawMessage().substring(1) + " 指令不存在，发送【#菜单】获取功能列表";
    }

    private void checkUserExist() {
        //todo 1. 查询用户是否存在，不存在则新增
        //获取用户直接返回
    }
}
