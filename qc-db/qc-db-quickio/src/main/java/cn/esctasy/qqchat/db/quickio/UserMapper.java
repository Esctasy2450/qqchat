package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.api.db.BaseAdapter;
import cn.esctasy.qqchat.api.db.IBaseMapper;
import cn.esctasy.qqchat.db.quickio.bean.IOUser;
import cn.esctasy.qqchat.db.quickio.config.QuickIoConfig;
import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserMapper implements IBaseMapper {

    private final DB db = QuickIoConfig.getDB();

    @Override
    public void save(BaseAdapter<?> t) {
        IOUser ioUser = new IOUser();
        BeanUtils.copyProperties(t.getData(), ioUser);

        Collection<IOUser> collection = db.collection(IOUser.class);
        collection.save(ioUser);
    }

    @Override
    public void batchSave(BaseAdapter<?> t) throws Exception {
        IOUser ioUser = new IOUser();
        BeanUtils.copyProperties(t.getData(), ioUser);

        Collection<IOUser> collection = db.collection(IOUser.class);
        // todo 将list中类转换成ioUser
        // collection.save(t.getData());
    }


    @Override
    public Object selectById(long id, Class<?> clazz) throws Exception {
        if (0L == id) {
            return null;
        }

        Collection<IOUser> collection = db.collection(IOUser.class);
        List<IOUser> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public List<Object> selectByIds(Class<?> clazz, long... id) {
        Collection<IOUser> collection = db.collection(IOUser.class);
        List<IOUser> list = collection.find(id);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        return Collections.singletonList(list);
    }

    @Override
    public int delById(Class<?> clazz, long... ids) {
        Collection<IOUser> collection = db.collection(IOUser.class);
        collection.delete(ids);

        return ids.length;
    }
}
