package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.user.UserCreateDto;
import rd.fomin.bookshop.model.entity.User;
import rd.fomin.bookshop.model.mapper.annotation.EncodedMapping;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RoleMapper.class, PasswordEncoderMapper.class})
public interface UserCreateMapper {
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    User toEntity(UserCreateDto userCreateDto);
}
