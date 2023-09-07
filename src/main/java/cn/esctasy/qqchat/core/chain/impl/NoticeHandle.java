package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.bean.escalation.notice.NoticeEs;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.notice.FriendAddHandle;
import cn.esctasy.qqchat.core.chain.impl.notice.PokeHandle;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 请求上报事件
 */

public class NoticeHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"notice".equals(code)) {
            this.goNext(code, metadata);
            return;
        }
        NoticeEs noticeEs = JSON.parseObject(metadata, NoticeEs.class);
        this.goChild(noticeEs.getNotice_type(), metadata);
    }
}
