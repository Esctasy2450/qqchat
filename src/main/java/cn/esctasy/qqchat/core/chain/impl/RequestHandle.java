package cn.esctasy.qqchat.core.chain.impl;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.request.FriendHandle;
import cn.esctasy.qqchat.core.bean.request.RequestEs;
import com.alibaba.fastjson.JSON;

/**
 * 请求上报事件
 * */
public class RequestHandle extends Handle {
    Handle friend = new FriendHandle();

    @Override
    public void handling(String code, String metadata) {
        if ("request".equals(code)) {
            RequestEs requestEs = JSON.parseObject(metadata, RequestEs.class);
            friend.handling(requestEs.getRequest_type(), metadata);
            return;
        }

        this.goNext(code, metadata, "RequestHandle");
    }
}
