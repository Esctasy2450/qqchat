package cn.esctasy.qqchat.core.common.chain;

import cn.esctasy.qqchat.core.event.chain.Handle;
import cn.esctasy.qqchat.core.event.chain.impl.EventHandle;
import cn.esctasy.qqchat.core.event.chain.impl.MessageHandle;
import cn.esctasy.qqchat.core.event.chain.impl.NoticeHandle;
import cn.esctasy.qqchat.core.event.chain.impl.RequestHandle;
import cn.esctasy.qqchat.core.event.chain.impl.event.HeartHandle;
import cn.esctasy.qqchat.core.event.chain.impl.event.LifeCycleHandle;
import cn.esctasy.qqchat.core.event.chain.impl.message.GroupHandle;
import cn.esctasy.qqchat.core.event.chain.impl.message.PrivateHandle;
import cn.esctasy.qqchat.core.event.chain.impl.notice.FriendAddHandle;
import cn.esctasy.qqchat.core.event.chain.impl.notice.NotifyHandle;
import cn.esctasy.qqchat.core.event.chain.impl.notice.notify.PokeHandle;
import cn.esctasy.qqchat.core.event.chain.impl.request.FriendHandle;

/**
 * 初始化责任链
 */
public class BaseChain implements ChainInterface {
    @Override
    public Handle initChain() {
        //事件调用
        return event().setNext(
                //消息调用
                message().setNext(
                        //上报请求调用
                        request().setNext(
                                //系统提示调用
                                notice()
                        )
                )
        );
    }

    private static Handle notice() {
        return new NoticeHandle().setChild(noticeChild());
    }

    private static Handle noticeChild() {
        return new FriendAddHandle().setNext(
                new NotifyHandle().setChild(new PokeHandle())
        );
    }

    private static Handle request() {
        return new RequestHandle().setChild(new FriendHandle());
    }

    private static Handle message() {
        return new MessageHandle().setChild(messageChild());
    }

    private static Handle messageChild() {
        return new PrivateHandle().setNext(new GroupHandle());
    }

    private static Handle event() {
        return new EventHandle().setChild(eventChild());
    }

    private static Handle eventChild() {
        return new HeartHandle().setNext(new LifeCycleHandle());
    }
}
