package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.bean.escalation.notice.NoticeEs;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.notice.FriendAddHandle;
import com.alibaba.fastjson.JSON;

/**
 * 请求上报事件
 * */
public class NoticeHandle extends Handle {
    Handle friend = new FriendAddHandle();

    @Override
    public void handling(String code, String metadata) {
        if (!"notice".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        NoticeEs noticeEs = JSON.parseObject(metadata, NoticeEs.class);
        friend.handling(noticeEs.getNotice_type(), metadata);
    }
}
