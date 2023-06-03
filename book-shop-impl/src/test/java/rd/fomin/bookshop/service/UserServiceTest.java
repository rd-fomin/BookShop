package rd.fomin.bookshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;
import rd.fomin.bookshop.model.dto.RoleInfoDto;
import rd.fomin.bookshop.model.dto.user.UserAuthDto;
import rd.fomin.bookshop.model.dto.user.UserInfoDto;
import rd.fomin.bookshop.model.entity.Role;
import rd.fomin.bookshop.model.entity.User;
import rd.fomin.bookshop.model.enums.ERole;
import rd.fomin.bookshop.model.mapper.RoleInfoMapper;
import rd.fomin.bookshop.model.mapper.RoleMapper;
import rd.fomin.bookshop.model.mapper.UserAuthMapper;
import rd.fomin.bookshop.model.mapper.UserCreateMapper;
import rd.fomin.bookshop.model.mapper.UserInfoMapper;
import rd.fomin.bookshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final Long USER_ID = 1L;
    private static final String USER_EMAIL = "some@some";
    private static final String WRONG_USER_EMAIL = "   %s   ".formatted(USER_EMAIL);

    private @Mock UserRepository repository;
    private @Spy UserInfoMapper userInfoMapper = Mappers.getMapper(UserInfoMapper.class);
    private @Spy UserCreateMapper userCreateMapper = Mappers.getMapper(UserCreateMapper.class);
    private @Spy UserAuthMapper userAuthMapper = Mappers.getMapper(UserAuthMapper.class);
    private RoleInfoMapper roleInfoMapper = Mappers.getMapper(RoleInfoMapper.class);
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);
    private @Captor ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);

    private @InjectMocks UserService userService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userInfoMapper, "roleInfoMapper", roleInfoMapper);
        ReflectionTestUtils.setField(userAuthMapper, "roleMapper", roleMapper);
    }

    @Test
    @DisplayName("users is not empty")
    void getAll() {
        doReturn(List.of(user())).when(repository).findAll();

        var actual = userService.getAll();
        var expected = userInfo();

        assertThat(actual)
                .isNotEmpty()
                .element(0).isEqualTo(expected);
    }

    @Test
    void create() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    @DisplayName("user not found")
    void throwsExceptionIdDoesntExist() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> userService.getOne(USER_ID)
        );
    }

    @Test
    void getOneByEmail() {
        doReturn(Optional.of(user())).when(repository).findByEmailIgnoreCase(USER_EMAIL);

        var actual = userService.getOneByEmail(WRONG_USER_EMAIL);

        verify(repository, times(1)).findByEmailIgnoreCase(stringCaptor.capture());

        assertThat(stringCaptor)
                .returns(USER_EMAIL, ArgumentCaptor::getValue);

        assertThat(actual)
                .isNotNull()
                .returns(USER_ID, UserAuthDto::id)
                .returns("some", UserAuthDto::getUsername);
    }

    private UserInfoDto userInfo() {
        return UserInfoDto.builder()
                .id(USER_ID)
                .username("some")
                .email(USER_EMAIL)
                .password("***********")
                .roles(Set.of(userRole()))
                .build();
    }

    private RoleInfoDto userRole() {
        return new RoleInfoDto(ERole.ROLE_USER.explain());
    }

    private User user() {
        return new User()
                .setId(1L)
                .setUsername("some")
                .setEmail(USER_EMAIL)
                .setPassword("12345")
                .setRoles(Set.of(role()));
    }

    private Role role() {
        return new Role()
                .setId(1L)
                .setName(ERole.ROLE_USER);
    }
}