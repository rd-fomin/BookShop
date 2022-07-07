package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.AuthorDto;
import rd.fomin.bookshop.model.entity.Author;

import java.util.List;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {
    Author toAuthor(AuthorDto authorDto);

    AuthorDto toAuthorDto(Author author);

    default List<Long> toIds(List<Author> author) {
        return Optional.ofNullable(author)
                .orElseGet(List::of)
                .stream()
                .map(Author::getId)
                .toList();
    }
}
