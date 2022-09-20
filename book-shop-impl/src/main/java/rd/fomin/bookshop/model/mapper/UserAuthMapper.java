package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.user.UserAuthDto;
import rd.fomin.bookshop.model.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { RoleMapper.class })
public interface UserAuthMapper {
    UserAuthDto toDto(User user);
}
