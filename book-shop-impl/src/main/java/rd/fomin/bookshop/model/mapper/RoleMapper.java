package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.RoleDto;
import rd.fomin.bookshop.model.entity.Role;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {
    RoleDto toDto(Role role);

    Set<RoleDto> toDtos(Set<Role> roles);
}
