package rd.fomin.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.dto.BookDto;
import rd.fomin.bookshop.model.dto.BookFilter;
import rd.fomin.bookshop.service.BookService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> get(BookFilter filter) {
        var books = bookService.getAllByFilter(filter);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> post(@RequestBody @Validated BookDto book,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            return ResponseEntity.unprocessableEntity().build();
        }
        var saved = bookService.add(book);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<BookDto> put(@RequestBody @Validated BookDto book,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            return ResponseEntity.unprocessableEntity().build();
        }
        var edited = bookService.edit(book);
        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
