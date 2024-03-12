package cn.esctasy.qqchat.db.common.constant;

import cn.esctasy.qqchat.api.event.bean.event.HeartBeatEs;
import cn.esctasy.qqchat.api.event.bean.message.GroupEs;
import cn.esctasy.qqchat.api.event.bean.message.PrivateEs;
import cn.esctasy.qqchat.api.event.bean.notice.notify.PokeEs;
import cn.esctasy.qqchat.api.event.bean.request.FriendEs;
import cn.esctasy.qqchat.api.event.handle.event.HeartEvent;
import cn.esctasy.qqchat.api.event.handle.message.GroupEvent;
import cn.esctasy.qqchat.api.event.handle.message.PrivateEvent;
import cn.esctasy.qqchat.api.event.handle.notice.notify.PokeEvent;
import cn.esctasy.qqchat.api.event.handle.request.FriendEvent;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * api接口到数据库的映射
 * 因为普通数据的存储采用纯切面的方式，所以便引入这种侵入度比较低的方式
 * 切面可以获取对应的接口，从而能获取到对应的数据的类型，对不同种类的数据处理有差异
 * 比如不同类型的数据对应不同的数据格式，关系型数据库中会存入不同的表
 */
@AllArgsConstructor
public enum Api2DBMapper {
    HEART(HeartEvent.class, HeartBeatEs.class),
    MESSAGE_GROUP(GroupEvent.class, GroupEs.class),
    MESSAGE_PRIVATE(PrivateEvent.class, PrivateEs.class),
    NOTICE_POKE(PokeEvent.class, PokeEs.class),
    REQUEST_FRIEND(FriendEvent.class, FriendEs.class);
    
    private static final Map<Class<?>, Class<?>> map = new HashMap<>();
    
    static {
        for (Api2DBMapper mapper : Api2DBMapper.values()) {
            map.put(mapper.event, mapper.clazz);
        }
    }

    /**
     * 接口
     */
    private final Class<?> event;

    /**
     * 对应接口触发时对应的数据格式实体类
     */
    private final Class<?> clazz;
    
    public static Class<?> getClazzByApi(Class<?> e) throws Exception {
        if (null == e) {
            throw new NullPointerException();
        }
        
        Class<?> clz = map.get(e);
        if (null == clz) {
            throw new Exception("接口不存在对象映射");
        }
        
        return clz;
    }
}
