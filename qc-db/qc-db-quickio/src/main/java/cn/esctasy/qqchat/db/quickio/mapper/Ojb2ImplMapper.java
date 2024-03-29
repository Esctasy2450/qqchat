package cn.esctasy.qqchat.db.quickio.mapper;

import cn.esctasy.qqchat.api.bean.User;
import cn.esctasy.qqchat.api.db.IBaseMapper;
import cn.esctasy.qqchat.db.quickio.UserMapper;
import cn.hutool.extra.spring.SpringUtil;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 实体类和持久化实现的映射关系
 */
@AllArgsConstructor
public enum Ojb2ImplMapper {
    USER(User.class, SpringUtil.getBean(UserMapper.class));
    
    private final Class<?> source;
    private final IBaseMapper target;

    private static final Map<Class<?>, IBaseMapper> map = new HashMap<>();
    static {
        for (Ojb2ImplMapper o : Ojb2ImplMapper.values()) {
            map.put(o.source, o.target);
        }
    }
    
    public static IBaseMapper getImpl(@NotNull Class<?> clazz) throws Exception {
        if (null == clazz) {
            return null;
        }

        IBaseMapper impl = map.get(clazz);
        if (null == impl) {
            throw new Exception("未找到实现，class: " + clazz.getName());
        }
        
        return impl;
    }
}
