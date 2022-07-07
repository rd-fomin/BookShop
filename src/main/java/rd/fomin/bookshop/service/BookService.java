package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import rd.fomin.bookshop.exception.BookShopException;
import rd.fomin.bookshop.model.dto.BookDto;
import rd.fomin.bookshop.model.mapper.BookMapper;
import rd.fomin.bookshop.repo.BookRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public BookDto getOne(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't find book by id(%d)".formatted(id)));
    }

    public BookDto add(BookDto book) {
        return Optional.ofNullable(book)
                .map(b -> b.setId(null))
                .map(bookMapper::toBook)
                .map(bookRepository::save)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't save book(%s)".formatted(book)));
    }

    public BookDto edit(BookDto book) {
        return Optional.ofNullable(book)
                .filter(b -> b.getId() != null)
                .map(bookMapper::toBook)
                .map(bookRepository::save)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't edit book(%s)".formatted(book)));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
