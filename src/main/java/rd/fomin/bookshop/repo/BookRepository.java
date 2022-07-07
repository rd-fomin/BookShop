package rd.fomin.bookshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rd.fomin.bookshop.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
