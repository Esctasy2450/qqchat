package cn.esctasy.qqchat.core;

import cn.esctasy.qqchat.common.ws.WsExample;
import cn.esctasy.qqchat.config.WebSocketConfig;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/private")
public class PrivateMsg {

    @GetMapping("/send")
    public void send() {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", 321516532);
        param.put("message", "321516532");
        param.put("auto_escape", true);
        WsExample.getWs().send(Reply.build("send_private_msg", param, "test"));
    }
}
