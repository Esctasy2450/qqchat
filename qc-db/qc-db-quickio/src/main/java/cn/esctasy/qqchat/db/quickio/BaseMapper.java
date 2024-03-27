package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.api.db.IBaseMapper;
import cn.esctasy.qqchat.api.db.IBaseMethod;
import cn.esctasy.qqchat.db.quickio.config.QuickIoConfig;
import cn.hutool.extra.spring.SpringUtil;
import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseMapper implements IBaseMapper {
    private static final DB db = QuickIO.usingDB(SpringUtil.getBean(QuickIoConfig.class).getConfig());

    /**
     * 保存
     */
    @Override
    public void save(BaseMethod t, Class<BaseMethod> clazz) {

    }

    /**
     * 批量保存
     */
    @Override
    public void save(List<BaseMethod> t, Class<BaseMethod> clazz) {
        Collection<BaseMethod> collection = db.collection(clazz);
        collection.save(t);
    }

    /**
     * 根据主键查询
     */
    public BaseMethod selectById(long id, Class<BaseMethod> clazz) {
        if (0L == id) {
            return null;
        }

        Collection<BaseMethod> collection = db.collection(clazz);
        List<BaseMethod> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 根据主键批量查询
     */
    public List<BaseMethod> selectByIds(Class<BaseMethod> clazz, long... id) {
        Collection<BaseMethod> collection = db.collection(clazz);
        List<BaseMethod> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        return list;
    }

    /**
     * 根据主键删除
     */
    public int delById(Class<BaseMethod> clazz, long... ids) {
        Collection<BaseMethod> collection = db.collection(clazz);
        collection.delete(ids);

        return ids.length;
    }

    @Override
    public <T extends IBaseMethod> void save(T t, Class<?> clazz) {

        Collection<BaseMethod> collection = db.collection(clazz);
        collection.save(t);
    }

    @Override
    public <T extends IBaseMethod> void save(List<T> t, Class<T> clazz) {

    }

    @Override
    public <T extends IBaseMethod> T selectById(long id, Class<T> clazz) {
        return null;
    }

    @Override
    public <T extends IBaseMethod> List<T> selectByIds(Class<T> clazz, long... id) {
        return null;
    }

    @Override
    public <T extends IBaseMethod> int delById(Class<T> clazz, long... ids) {
        return 0;
    }
}
