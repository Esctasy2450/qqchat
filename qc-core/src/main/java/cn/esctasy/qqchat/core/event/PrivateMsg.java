package cn.esctasy.qqchat.core.event;

import cn.esctasy.qqchat.api.reply.bean.Reply;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
