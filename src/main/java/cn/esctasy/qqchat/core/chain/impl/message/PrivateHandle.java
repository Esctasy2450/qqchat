package cn.esctasy.qqchat.core.chain.impl.message;

import cn.esctasy.qqchat.common.third.QingYunConfig;
import cn.esctasy.qqchat.common.utils.SpringContextHolder;
import cn.esctasy.qqchat.common.ws.WsExample;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import cn.esctasy.qqchat.core.chain.Handle;
import cn.esctasy.qqchat.core.bean.escalation.message.PrivateEs;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class PrivateHandle extends Handle {

    @Override
    public void handling(String code, String metadata) {
        if (!"private".equals(code)) {
            this.goNext(code, metadata);
            return;
        }

        PrivateEs privateEs = JSON.parseObject(metadata, PrivateEs.class);

        String message = getMsg(privateEs.getRaw_message());

        Map<String, Object> param = new HashMap<>();
        param.put("user_id", privateEs.getUser_id());
        param.put("message", message);
        param.put("auto_escape", true);
        WsExample.getWs().send(Reply.build("send_private_msg", param));
    }

    private String getMsg(String message) {
        return SpringContextHolder.getBean(QingYunConfig.class).getQingYunMsg(message);
    }
}
