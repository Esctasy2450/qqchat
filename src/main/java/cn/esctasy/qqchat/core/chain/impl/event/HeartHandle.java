package cn.esctasy.qqchat.core.chain.impl.event;

import cn.esctasy.qqchat.common.utils.ChainKeyWords;
import cn.esctasy.qqchat.core.bean.escalation.event.HeartBeatEs;
import cn.esctasy.qqchat.core.chain.Handle;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件责任链
 * 心跳事件
 */
@Slf4j
public class HeartHandle extends Handle {

    @Override
    public void handling(String metadata) {
        if (!metadata.contains(ChainKeyWords.getPtEtHeartBeat())) {
            this.goNext(metadata);
            return;
        }

        HeartBeatEs heartBeatEs = JSON.parseObject(metadata, HeartBeatEs.class);
        log.info("心跳状态：{}", heartBeatEs);
    }
}
