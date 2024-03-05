package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.api.db.QQChatDSHandleForAop;
import org.springframework.stereotype.Component;

/**
 * 切面触发时，执行的方法
 * */
@Component
public class QQChatDSHandleForAopImpl implements QQChatDSHandleForAop {
    @Override
    public void before() {
        System.out.println("before");
    }

    @Override
    public void after() {
        System.out.println("after");
    }
}
