package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.user.UserInfoDto;
import rd.fomin.bookshop.model.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = { RoleInfoMapper.class, PasswordEncoderMapper.class })
public interface UserInfoMapper {

    @Mapping(target = "password", constant = "***********")
    UserInfoDto toDto(User user);
}
