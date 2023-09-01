package cn.esctasy.qqchat.core;

import cn.esctasy.qqchat.config.WebSocketConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/private")
public class PrivateMsg {
    @Autowired
    WebSocketConfig config;

    @GetMapping("/send")
    public void send() {
        config.getWs().send("{\n" +
                "    \"action\": \"send_private_msg\",\n" +
                "    \"params\": {\n" +
                "        \"user_id\": 321516532,\n" +
                "        \"message\": \"test\",\n" +
                "        \"auto_escape\": true\n" +
                "    },\n" +
                "    \"echo\": \"'回声', 如果指定了 echo 字段, 那么响应包也会同时包含一个 echo 字段, 它们会有相同的值\"\n" +
                "}");
    }
}
