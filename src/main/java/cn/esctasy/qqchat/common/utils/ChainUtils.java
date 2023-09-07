package cn.esctasy.qqchat.common.utils;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.chain.impl.EventHandle;
import cn.esctasy.qqchat.core.chain.impl.MessageHandle;
import cn.esctasy.qqchat.core.chain.impl.NoticeHandle;
import cn.esctasy.qqchat.core.chain.impl.RequestHandle;
import cn.esctasy.qqchat.core.chain.impl.event.HeartHandle;
import cn.esctasy.qqchat.core.chain.impl.event.LifeCycle;
import cn.esctasy.qqchat.core.chain.impl.message.GroupHandle;
import cn.esctasy.qqchat.core.chain.impl.message.PrivateHandle;
import cn.esctasy.qqchat.core.chain.impl.notice.FriendAddHandle;
import cn.esctasy.qqchat.core.chain.impl.notice.PokeHandle;
import cn.esctasy.qqchat.core.chain.impl.request.FriendHandle;

/**
 * 初始化调用链
 */
public class ChainUtils {
    public static Handle initChain() {
        //事件调用
        return new EventHandle()
                .setNext(new MessageHandle()
                        .setNext()
                        .setChild(new PrivateHandle()
                                .setNext(new GroupHandle())))
                .setChild(new HeartHandle()
                        .setNext(new LifeCycle()));


    }

    private static Handle notice() {
        return new NoticeHandle().setChild(noticeChild());
    }

    private static Handle noticeChild() {
        return new FriendAddHandle().setNext(new PokeHandle());
    }

    private static Handle request() {
        new RequestHandle().setChild(new FriendHandle())
    }
}
