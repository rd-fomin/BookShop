package rd.fomin.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rd.fomin.bookshop.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}