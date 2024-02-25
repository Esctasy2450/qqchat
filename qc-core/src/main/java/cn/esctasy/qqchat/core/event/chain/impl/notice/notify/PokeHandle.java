package cn.esctasy.qqchat.core.event.chain.impl.notice.notify;

import cn.esctasy.qqchat.api.event.handle.notice.notify.PokeEvent;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.hutool.extra.spring.SpringUtil;

/**
 * 提醒
 * 戳一戳
 */
public class PokeHandle extends Handle {

    @Override
    public void handling(String metadata) {
        SpringUtil.getBean(PokeEvent.class).eventHandle(metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtNtStPoke();
    }
}
