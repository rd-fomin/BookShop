package rd.fomin.bookshop.model.dto;

import lombok.Builder;

@Builder
public record BookFilter(String name,
                         Long count) {
}
