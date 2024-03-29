package cn.esctasy.qqchat.db.mysql;

import cn.esctasy.qqchat.api.db.BaseAdapter;
import cn.esctasy.qqchat.api.db.IBaseMapper;

import java.util.List;

public class BaseMapper implements IBaseMapper {
   @Override
   public void save(BaseAdapter<?> t) throws Exception {
      
   }

   @Override
   public void batchSave(BaseAdapter<?> t) throws Exception {

   }

   @Override
   public Object selectById(long id, Class<?> clazz) throws Exception {
      return null;
   }

   @Override
   public List<Object> selectByIds(Class<?> clazz, long... id) throws Exception {
      return null;
   }

   @Override
   public int delById(Class<?> clazz, long... ids) throws Exception {
      return 0;
   }
}
