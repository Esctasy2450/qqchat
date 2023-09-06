package cn.esctasy.qqchat.core;

import cn.esctasy.qqchat.core.bean.reply.Reply;
import com.pkslow.ai.GoogleBardClient;
import com.pkslow.ai.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/private")
public class PrivateMsg {

    private final GoogleBardClient client;
    private final WebSocketClient webSocketClient;

    @GetMapping("/send")
    public void send() {
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", 321516532);
        param.put("message", "321516532");
        param.put("auto_escape", true);
        Reply.build("send_private_msg", param, "test").send();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam("q") String question) {
//        AIClient client = new GoogleBardClient("agihvCUwVwpTj1bqSNqhgYWVuswAyCIPAygj4ETwv6KpkJwQsB_9DrLcYAqKDEu7qKZwvw.;APoG2W8Lj2icGWOdxpgGgKgzVuE7uXfLTiDtv4KOZYArrnlfQ_C0BkIHWdfAJP7J63gG-Bvxeg");
        Answer answer = client.ask(question);
        System.out.println(answer);
        return answer.getChosenAnswer();
//        throw new RuntimeException("Can't access to Google Bard");

    }
}
