package rd.fomin.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.Book;
import rd.fomin.bookshop.service.BookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService service;

    @GetMapping("/books")
    public List<Book> books() {
        var books = service.books();
        log.info("Все книги в системе: {}", books);
        return books;
    }

    @PostMapping("/books/add")
    public Book add(@RequestParam String name) {
        var book = new Book()
                .name(name);
        var saved = service.add(book);
        log.info("Добавлена новая книга: {}", saved);
        return saved;
    }
}
