package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.message.GroupHandle;
import cn.esctasy.qqchat.core.chain.impl.message.PrivateHandle;
import cn.esctasy.qqchat.core.bean.escalation.message.MessageEs;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息责任链
 */
@Slf4j
public class MessageHandle extends Handle {
    private final Handle privateHd = ;
    private final Handle group = ;

    {
        privateHd.setNext(group);
    }

    @Override
    public void handling(String code, String metadata) {
        if (!"message".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        MessageEs messageEs = JSON.parseObject(metadata, MessageEs.class);
        privateHd.handling(messageEs.getMessage_type(), metadata);
    }
}
