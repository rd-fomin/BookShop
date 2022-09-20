package rd.fomin.bookshop.model.dto;

import org.springframework.security.core.GrantedAuthority;
import rd.fomin.bookshop.model.enums.ERole;

import java.io.Serializable;

public record RoleDto(Long id,
                      ERole name) implements GrantedAuthority, Serializable {
    @Override
    public String getAuthority() {
        return name.name();
    }
}
