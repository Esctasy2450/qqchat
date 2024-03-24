package cn.esctasy.qqchat.api.db;

/**
 * 基础增删改查接口
 * todo
 */
public interface QCDBBaseMapper {
    <T> void add(T obj);

    void update();

    void select();

    void delete();
}
