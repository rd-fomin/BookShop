package rd.fomin.bookshop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(chain = true, fluent = true)
@Builder
@Getter
public class BookFilter {
    private String name;
    private Long count;
}
