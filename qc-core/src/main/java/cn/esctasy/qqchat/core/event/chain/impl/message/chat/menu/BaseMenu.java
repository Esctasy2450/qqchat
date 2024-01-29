package cn.esctasy.qqchat.core.event.chain.impl.message.chat.menu;


import cn.esctasy.qqchat.core.event.chain.impl.message.chat.menu.impl.BaseMenuImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseMenu {
    BASE("菜单", new BaseMenuImpl());

    private final String label;
    private final MenuInterface impl;

    public static String parse(String instruct) {
        for (BaseMenu baseMenu : values()) {
            if (baseMenu.getLabel().equals(instruct)) {
                return baseMenu.getImpl().getRecv();
            }
        }

        return instruct + " 指令不存在，发送【#菜单】获取功能列表";
    }
}
