package cn.esctasy.qqchat.db.common.aop;

import cn.esctasy.qqchat.api.db.QQChatDSHandleForAop;
import cn.esctasy.qqchat.db.common.constant.Api2DBMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class QQChatDSAop {
    private final QQChatDSHandleForAop qqChatDSHandleForAop;

    @Pointcut("execution(* cn.esctasy.qqchat.api.event.handle..*.eventHandle(..))")
    public void point() {
    }

    @Before("point()")
    @SneakyThrows
    public void before(JoinPoint joinpoint) {
        System.out.println("----------Before开始-----------");

        System.out.println("方法名：" + joinpoint.getSignature().getName());
        System.out.println("参数值集合：" + Arrays.asList(joinpoint.getArgs()));
        System.out.println("参数值类型：" + joinpoint.getArgs()[0].getClass().getTypeName());
        // 获取目标注解对象，Cacheab  leTest是自定义的一个注解
        String cacheable = ((MethodSignature) joinpoint.getSignature()).getName();
        String classType = joinpoint.getTarget().getClass().getName();
        System.out.println("目标注解对象：" + cacheable);
        System.out.println("获取目标方法所在类：" + classType);
        System.out.println(Arrays.toString(joinpoint.getTarget().getClass().getInterfaces()));

        Class<?>[] interfaces = findInterfacesWithMethod(joinpoint.getTarget().getClass(), joinpoint.getSignature().getName());
        Class<?> c = null;
        for (Class<?> intf : interfaces) {
            System.out.println(intf.getName());
            Class<?> cp = Api2DBMapper.getClazzByApi(intf);
            if (cp != null) {
                c = cp;
            }
        }
        if (c != null) {
            System.out.println(c.getName());
        }
        
        System.out.println("----------Before结束-----------");

        qqChatDSHandleForAop.before();
    }

    @After("point()")
    public void after() {
        qqChatDSHandleForAop.after();
    }


    public static Class<?>[] findInterfacesWithMethod(Class<?> targetClass, String methodName) {
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] interfaces = targetClass.getInterfaces();
                return filterInterfacesWithMethod(interfaces, methodName);
            }
        }
        return new Class<?>[0];
    }

    public static Class<?>[] filterInterfacesWithMethod(Class<?>[] interfaces, String methodName) {
        
        int count = 0;
        for (Class<?> intf : interfaces) {
            try {
                Method[] methods = intf.getMethods();
                intf.getMethod(methodName);
                count++;
            } catch (NoSuchMethodException ignore) {
                ignore.printStackTrace();
            }
        }
        Class<?>[] result = new Class<?>[count];
        count = 0;
        for (Class<?> intf : interfaces) {
            try {
                intf.getMethod(methodName);
                result[count++] = intf;
            } catch (NoSuchMethodException ignore) {
                ignore.printStackTrace();
            }
        }
        return result;
    }
}
