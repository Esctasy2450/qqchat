package cn.esctasy.qqchat.api.db;

import java.util.List;

public interface IBaseMapper {
    /**
     * 保存
     */
    void save(BaseAdapter<?> t) throws Exception;

    /**
     * 批量保存
     */
    void batchSave(BaseAdapter<?> t) throws Exception;

    /**
     * 根据主键查询
     */
    Object selectById(long id, Class<?> clazz) throws Exception;

    /**
     * 根据主键批量查询
     */
    List<Object> selectByIds(Class<?> clazz, long... id) throws Exception;

    /**
     * 根据主键删除
     */
    int delById(Class<?> clazz, long... ids) throws Exception;
}
