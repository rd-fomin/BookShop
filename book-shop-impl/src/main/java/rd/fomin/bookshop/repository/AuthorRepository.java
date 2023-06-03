package rd.fomin.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rd.fomin.bookshop.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}