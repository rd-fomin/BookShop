package rd.fomin.bookshop.model.dto.user;

import lombok.Builder;
import rd.fomin.bookshop.model.dto.RoleInfoDto;

import java.io.Serializable;
import java.util.Set;

@Builder
public record UserInfoDto(Long id,
                          String username,
                          String email,
                          String password,
                          Set<RoleInfoDto> roles) implements Serializable {
}
