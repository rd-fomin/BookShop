package rd.fomin.bookshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import rd.fomin.bookshop.exception.BookShopException;
import rd.fomin.bookshop.model.dto.AuthorDto;
import rd.fomin.bookshop.model.mapper.AuthorMapper;
import rd.fomin.bookshop.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toAuthorDto)
                .toList();
    }

    public AuthorDto add(AuthorDto author) {
        return Optional.ofNullable(author)
                .map(a -> a.setId(null))
                .map(authorMapper::toAuthor)
                .map(authorRepository::save)
                .map(authorMapper::toAuthorDto)
                .orElseThrow(() -> new BookShopException("Couldn't save book(%s)".formatted(author)));
    }
}
