package rd.fomin.bookshop.model.dto.user;

import rd.fomin.bookshop.model.dto.RoleDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public record UserCreateDto(@Size(max = 20) String username,
                            @Size(max = 50) @Email String email,
                            @NotBlank @Size(max = 30) String password,
                            Set<RoleDto> roleIds) implements Serializable {

}