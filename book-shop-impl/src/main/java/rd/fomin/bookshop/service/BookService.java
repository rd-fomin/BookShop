package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rd.fomin.bookshop.exception.BookShopException;
import rd.fomin.bookshop.model.dto.BookDto;
import rd.fomin.bookshop.model.dto.BookFilter;
import rd.fomin.bookshop.model.mapper.BookMapper;
import rd.fomin.bookshop.repository.BookRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public List<BookDto> getAllByFilter(BookFilter filter) {
        return bookRepository.findAllByFilter(filter).stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    public BookDto getOne(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't find book by id(%d)".formatted(id)));
    }

    @Transactional
    public BookDto add(BookDto book) {
        return Optional.ofNullable(book)
                .filter(b -> Objects.isNull(b.id()))
                .map(bookMapper::toBook)
                .map(bookRepository::save)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't save book(%s)".formatted(book)));
    }

    @Transactional
    public BookDto edit(BookDto book) {
        return Optional.ofNullable(book)
                .filter(b -> Objects.nonNull(b.id()))
                .map(bookMapper::toBook)
                .map(bookRepository::save)
                .map(bookMapper::toBookDto)
                .orElseThrow(() -> new BookShopException("Couldn't edit book(%s)".formatted(book)));
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
