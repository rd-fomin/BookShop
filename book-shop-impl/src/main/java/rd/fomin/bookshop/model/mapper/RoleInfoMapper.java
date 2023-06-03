package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.RoleInfoDto;
import rd.fomin.bookshop.model.entity.Role;
import rd.fomin.bookshop.model.enums.ERole;

import java.util.Optional;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleInfoMapper {
    @Mapping(target = "role", source = "name", qualifiedByName = "role")
    RoleInfoDto toDto(Role role);

    Set<RoleInfoDto> toDtos(Set<Role> roles);

    @Named("role")
    default String explain(ERole role) {
        return Optional.ofNullable(role)
                .map(ERole::explain)
                .orElse("Пользователь");
    }
}
