package rd.fomin.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rd.fomin.bookshop.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}