package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rd.fomin.bookshop.exception.BookShopException;
import rd.fomin.bookshop.model.dto.AuthorDto;
import rd.fomin.bookshop.model.mapper.AuthorMapper;
import rd.fomin.bookshop.repository.AuthorRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toAuthorDto)
                .toList();
    }

    @Transactional
    public AuthorDto add(AuthorDto author) {
        return Optional.ofNullable(author)
                .filter(a -> Objects.isNull(a.id()))
                .map(authorMapper::toAuthor)
                .map(authorRepository::save)
                .map(authorMapper::toAuthorDto)
                .orElseThrow(() -> new BookShopException("Couldn't save book(%s)".formatted(author)));
    }
}
