package cn.esctasy.qqchat.core;

import cn.esctasy.qqchat.common.utils.UuidUtils;
import cn.esctasy.qqchat.core.bean.reply.Reply;
import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/private")
public class PrivateMsg {
    private final GoogleBardClient client;

    @GetMapping("/send")
    public void send(@RequestBody Map<String, Object> param, @RequestParam String action) {
        Reply.build(action, param).send();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam("q") String question) {
        Answer answer = client.ask(question);
        System.out.println(answer);
        return answer.getChosenAnswer();
    }
}
