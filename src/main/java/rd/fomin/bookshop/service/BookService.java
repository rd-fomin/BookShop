package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rd.fomin.bookshop.exception.BookException;
import rd.fomin.bookshop.model.Book;
import rd.fomin.bookshop.repo.BookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getOne(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookException("Couldn't find book by id(%d)".formatted(id)));
    }

    public Book add(Book book) {
        return Optional.ofNullable(book)
                .map(b -> b.id(null))
                .map(bookRepository::save)
                .orElseThrow(() -> new BookException("Couldn't save book(%s)".formatted(book)));
    }

    public Book edit(Book book) {
        return Optional.ofNullable(book)
                .filter(b -> b.id() != null)
                .map(bookRepository::save)
                .orElseThrow(() -> new BookException("Couldn't edit book(%s)".formatted(book)));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
