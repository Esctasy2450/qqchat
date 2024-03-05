package cn.esctasy.qqchat.db.common.aop;

import cn.esctasy.qqchat.api.db.QQChatDSHandleForAop;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class QQChatDSAop {
    private final QQChatDSHandleForAop qqChatDSHandleForAop;
    
    @Pointcut("execution(* cn.esctasy.qqchat.api.event.handle..*.eventHandle(..))")
    public void point() {
    }
    
    @Before("point()")
    public void before() {
        qqChatDSHandleForAop.before();
    }
    
    @After("point()")
    public void after() {
        qqChatDSHandleForAop.after();
    }
}
