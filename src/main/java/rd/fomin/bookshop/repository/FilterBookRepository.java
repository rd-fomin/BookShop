package rd.fomin.bookshop.repository;

import rd.fomin.bookshop.model.dto.BookFilter;
import rd.fomin.bookshop.model.entity.Book;

import java.util.List;

public interface FilterBookRepository {
    List<Book> findAllByFilter(BookFilter filter);
}
