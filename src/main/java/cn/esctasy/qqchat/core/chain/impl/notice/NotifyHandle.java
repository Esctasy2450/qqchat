package cn.esctasy.qqchat.core.chain.impl.notice;

import cn.esctasy.qqchat.core.bean.escalation.notice.NoticeEs;
import cn.esctasy.qqchat.core.bean.escalation.notice.NotifyEs;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;

public class NotifyHandle extends Handle {
    @Override
    public void handling(String code, String metadata) {
        if (!"notify".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        NotifyEs notifyEs = JSON.parseObject(metadata, NotifyEs.class);
        this.goChild(notifyEs.getSub_type(), metadata);
    }
}
