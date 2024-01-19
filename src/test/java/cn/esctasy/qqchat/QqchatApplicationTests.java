package cn.esctasy.qqchat;

import cn.esctasy.qqchat.common.utils.UuidUtils;
import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
class QqchatApplicationTests {

    @Test
    void contextLoads() {
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
