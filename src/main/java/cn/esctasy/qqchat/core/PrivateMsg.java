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
//        AIClient client = new GoogleBardClient("agihvCUwVwpTj1bqSNqhgYWVuswAyCIPAygj4ETwv6KpkJwQsB_9DrLcYAqKDEu7qKZwvw.;APoG2W8Lj2icGWOdxpgGgKgzVuE7uXfLTiDtv4KOZYArrnlfQ_C0BkIHWdfAJP7J63gG-Bvxeg");
        Answer answer = client.ask(question);
        System.out.println(answer);
        return answer.getChosenAnswer();
//        throw new RuntimeException("Can't access to Google Bard");

    }

    @GetMapping("/testDb")
    public void gget() {
        try (DB db = QuickIO.usingDB("example_db")) {
            Collection<Book> collection = db.collection(Book.class);
            Book book = Book.of(b -> {
                b.name = UuidUtils.id();
                b.author = "Bruce Eckel";
                b.price = 129.8;
            });

            collection.save(book);
            System.out.println(book.objectId());
            List<Book> books = collection.findAll();
            books.forEach(IOEntity::printJson);
        }
    }

    public static class Book extends IOEntity {
        public String name;
        public String author;
        public Double price;

        public static Book of(Consumer<Book> consumer) {
            Book book = new Book();
            consumer.accept(book);
            return book;
        }
    }
}
