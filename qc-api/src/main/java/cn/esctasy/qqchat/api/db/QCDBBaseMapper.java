package cn.esctasy.qqchat.api.db;

/**
 * 基础增删改查接口
 * todo
 * 此处有问题，因为quickIo需要继承IOEntity类才可以使用，导致mysql之类的关系型数据库在切换时暂时没有办法无感切换
 */
public interface QCDBBaseMapper {
    <T> void add(T obj);

    void update();

    void select();

    void delete();
}
