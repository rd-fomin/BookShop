package rd.fomin.bookshop.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rd.fomin.bookshop.model.dto.user.UserAuthDto;
import rd.fomin.bookshop.model.dto.user.UserCreateDto;
import rd.fomin.bookshop.model.dto.user.UserInfoDto;
import rd.fomin.bookshop.model.mapper.UserAuthMapper;
import rd.fomin.bookshop.model.mapper.UserCreateMapper;
import rd.fomin.bookshop.model.mapper.UserInfoMapper;
import rd.fomin.bookshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final UserAuthMapper userAuthMapper;
    private final UserInfoMapper userInfoMapper;

    public List<UserInfoDto> getAll() {
        return userRepository.findAll().stream()
                .map(userInfoMapper::toDto)
                .toList();
    }

    @Transactional
    public UserInfoDto create(UserCreateDto userToCreate) {
        return Optional.of(userToCreate)
                .map(userCreateMapper::toEntity)
                .map(userRepository::save)
                .map(userInfoMapper::toDto)
                .orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userAuthMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user %s".formatted(username)));
    }

    public UserInfoDto getOne(Long id) {
        return userRepository.findById(id)
                .map(userInfoMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with id %d not found".formatted(id)));
    }

    public UserAuthDto getOneByEmail(@NonNull String email) {
        return userRepository.findByEmailIgnoreCase(email.trim())
                .map(userAuthMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with email %s not found".formatted(email)));
    }
}
