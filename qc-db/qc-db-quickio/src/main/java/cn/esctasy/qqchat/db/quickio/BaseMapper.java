package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.api.db.BaseAdapter;
import cn.esctasy.qqchat.api.db.IBaseMapper;
import cn.esctasy.qqchat.db.quickio.mapper.Ojb2ImplMapper;
import java.util.List;

public class BaseMapper implements IBaseMapper {
    /**
     * 保存
     */
    @Override
    public void save(BaseAdapter<?> t) throws Exception {
        Ojb2ImplMapper.getImpl(t.getClazz()).save(t);
    }

    /**
     * 批量保存
     */
    @Override
    public void batchSave(BaseAdapter<?> t) throws Exception {
        Ojb2ImplMapper.getImpl(t.getClazz()).batchSave(t);
    }

    /**
     * 根据主键查询
     */
    @Override
    public Object selectById(long id, Class<?> clazz) throws Exception {
        return Ojb2ImplMapper.getImpl(clazz).selectById(id, clazz);
    }

    /**
     * 根据主键批量查询
     */
    @Override
    public List<Object> selectByIds(Class<?> clazz, long... id) throws Exception {
        return Ojb2ImplMapper.getImpl(clazz).selectByIds(clazz, id);
    }

    /**
     * 根据主键删除
     */
    @Override
    public int delById(Class<?> clazz, long... ids) throws Exception {
        return Ojb2ImplMapper.getImpl(clazz).delById(clazz, ids);
    }
}
