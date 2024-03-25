package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.db.quickio.config.QuickIoConfig;
import cn.hutool.extra.spring.SpringUtil;
import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseMapper {
    private static final DB db = QuickIO.usingDB(SpringUtil.getBean(QuickIoConfig.class).getConfig());

    /**
     * 保存
     */
    public <T extends IOEntity> void save(T t, Class<T> clazz) {
        Collection<T> collection = db.collection(clazz);
        collection.save(t);
    }

    /**
     * 批量保存
     */
    public <T extends IOEntity> void save(List<T> t, Class<T> clazz) {
        Collection<T> collection = db.collection(clazz);
        collection.save(t);
    }

    /**
     * 根据主键查询
     */
    public <T extends IOEntity> T selectById(long id, Class<T> clazz) {
        if (0L == id) {
            return null;
        }

        Collection<T> collection = db.collection(clazz);
        List<T> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 根据主键批量查询
     */
    public <T extends IOEntity> List<T> selectByIds(Class<T> clazz, long... id) {
        Collection<T> collection = db.collection(clazz);
        List<T> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        return list;
    }

    /**
     * 根据主键删除
     */
    public <T extends IOEntity> int delById(Class<T> clazz, long... ids) {
        Collection<T> collection = db.collection(clazz);
        collection.delete(ids);
        
        return ids.length;
    }
}
