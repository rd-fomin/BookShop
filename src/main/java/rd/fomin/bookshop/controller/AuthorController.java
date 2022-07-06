package rd.fomin.bookshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.dto.AuthorDto;
import rd.fomin.bookshop.service.AuthorService;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> get() {
        var authors = authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> post(@RequestBody AuthorDto book) {
        var saved = authorService.add(book);
        return ResponseEntity.ok(saved);
    }
}
