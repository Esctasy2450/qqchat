package cn.esctasy.qqchat.db.quickio;

import cn.esctasy.qqchat.api.db.QCDBBaseMapper;
import com.github.artbits.quickio.core.IOEntity;
import org.springframework.stereotype.Service;

/**
 * 基础增删改查实现类
 */
@Service
public class QCDBBaseMapperImpl implements QCDBBaseMapper {


    @Override
    public <T> void add(T obj) {
        obj.getClass();
    }

    @Override
    public void update() {
        //new BaseMapper().save();
    }

    @Override
    public void select() {

    }

    @Override
    public void delete() {

    }
}
