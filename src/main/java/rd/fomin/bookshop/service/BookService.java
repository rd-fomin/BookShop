package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rd.fomin.bookshop.model.Book;
import rd.fomin.bookshop.repo.BookRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository repository;

    public List<Book> books() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }

    public Book add(Book book) {
        return repository.save(book);
    }
}
