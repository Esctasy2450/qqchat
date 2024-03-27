package cn.esctasy.qqchat.api.db;

import java.util.List;

public interface IBaseMapper {
    /**
     * 保存
     */
    <T extends IBaseMethod> void save(T t, Class<?> clazz);

    /**
     * 批量保存
     */
    <T extends IBaseMethod> void save(List<T> t, Class<T> clazz);

    /**
     * 根据主键查询
     */
    <T extends IBaseMethod> T selectById(long id, Class<T> clazz);

    /**
     * 根据主键批量查询
     */
    <T extends IBaseMethod> List<T> selectByIds(Class<T> clazz, long... id);

    /**
     * 根据主键删除
     */
    <T extends IBaseMethod> int delById(Class<T> clazz, long... ids);
}
