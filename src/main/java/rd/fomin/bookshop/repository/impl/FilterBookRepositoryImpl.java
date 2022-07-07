package rd.fomin.bookshop.repository.impl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import rd.fomin.bookshop.model.dto.BookFilter;
import rd.fomin.bookshop.model.entity.Book;
import rd.fomin.bookshop.repository.FilterBookRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter(onMethod = @__(@Autowired))
public class FilterBookRepositoryImpl implements FilterBookRepository {
    private EntityManager manager;

    @Override
    public List<Book> findAllByFilter(BookFilter filter) {
        var cb = manager.getCriteriaBuilder();
        var cq = cb.createQuery(Book.class);
        var root = cq.from(Book.class);
        var select = cq.select(root);
        var predicates = new ArrayList<Predicate>();

        Optional.ofNullable(filter.count())
                .ifPresent(count -> predicates.add(cb.equal(root.get("count"), count)));
        Optional.ofNullable(filter.name())
                .ifPresent(name -> predicates.add(cb.like(root.get("name"), "%%%s%%".formatted(name))));

        select.where(cb.and(predicates.toArray(Predicate[]::new)));
        return manager.createQuery(select).getResultList();
    }
}
