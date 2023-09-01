package cn.esctasy.qqchat.core.chain.impl.request;

import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.config.WebSocketConfig;
import cn.esctasy.qqchat.core.bean.request.FriendEs;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FriendHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if ("friend".equals(code)) {
            WebSocketConfig webSocketConfig = SpringContextHolder.getBean(WebSocketConfig.class);
            FriendEs friendEs = JSON.parseObject(metadata, FriendEs.class);
            webSocketConfig.getWs().send("{\n" +
                    "    \"action\": \"set_friend_add_request\",\n" +
                    "    \"params\": {\n" +
                    "        \"flag\": "+ friendEs.getFlag() +",\n" +
                    "        \"approve\": true\n" +
                    "    },\n" +
                    "    \"echo\": \"'回声', 如果指定了 echo 字段, 那么响应包也会同时包含一个 echo 字段, 它们会有相同的值\"\n" +
                    "}");
            log.info(metadata);
            return;
        }

        this.goNext(code, metadata, "FriendHandle");
    }
}
