package rd.fomin.bookshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rd.fomin.bookshop.model.dto.BookDto;
import rd.fomin.bookshop.model.entity.Book;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AuthorMapper.class)
public interface BookMapper {
    Book toBook(BookDto bookDto);

    @Mapping(target = "authorIds", source = "authors")
    BookDto toBookDto(Book book);
}
