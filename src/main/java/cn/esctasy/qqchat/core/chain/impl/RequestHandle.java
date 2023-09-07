package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.request.FriendHandle;
import cn.esctasy.qqchat.core.bean.escalation.request.RequestEs;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 请求上报事件
 */
public class RequestHandle extends Handle {
    private final Handle friend = new FriendHandle();

    public RequestHandle(Handle next, Handle child) {
        super(next, child);
    }

    @Override
    public void handling(String code, String metadata) {
        if (!"request".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        RequestEs requestEs = JSON.parseObject(metadata, RequestEs.class);
        friend.handling(requestEs.getRequest_type(), metadata);
    }
}
