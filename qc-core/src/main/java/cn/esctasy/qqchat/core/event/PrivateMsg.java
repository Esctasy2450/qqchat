package cn.esctasy.qqchat.core.event;

import cn.esctasy.qqchat.api.reply.bean.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/private")
public class PrivateMsg {

    @GetMapping("/send")
    public void send(@RequestBody Map<String, Object> param, @RequestParam String action) {
        Reply.build(action, param).send();
    }
}
