package cn.esctasy.qqchat.core.event.chain.impl.event;

import cn.esctasy.qqchat.api.event.bean.event.LifeCycleEs;
import cn.esctasy.qqchat.api.local.LocalInfo;
import cn.esctasy.qqchat.core.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.event.chain.Handle;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 生命周期事件
 * */
@Slf4j
public class LifeCycleHandle extends Handle {

    @Override
    public void handling(String metadata) {
        LifeCycleEs lifeCycleEs = JSON.parseObject(metadata, LifeCycleEs.class);
        LocalInfo.setSelf_id(lifeCycleEs.getSelf_id());
        log.info("lifecycle, {}", metadata);
    }

    @Override
    public String keyword() {
        return ChainKeyWords.getPtEtLifeCycle();
    }
}
