package rd.fomin.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.Book;
import rd.fomin.bookshop.service.BookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> get() {
        var books = bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Book> post(@RequestBody Book book) {
        var saved = bookService.add(book);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<Book> put(@RequestBody Book book) {
        var edited = bookService.edit(book);
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
