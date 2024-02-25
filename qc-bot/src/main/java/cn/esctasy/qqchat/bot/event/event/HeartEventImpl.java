package cn.esctasy.qqchat.bot.event.event;

import cn.esctasy.qqchat.api.event.bean.event.HeartBeatEs;
import cn.esctasy.qqchat.api.event.handle.event.HeartEvent;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeartEventImpl implements HeartEvent {
    @Override
    public void eventHandle(String metadata) {
        HeartBeatEs heartBeatEs = JSON.parseObject(metadata, HeartBeatEs.class);
        log.info("心跳状态：{}", heartBeatEs);
    }
}
