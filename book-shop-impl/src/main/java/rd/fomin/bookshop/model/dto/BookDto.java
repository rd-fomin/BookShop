package rd.fomin.bookshop.model.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
public record BookDto(Long id,
                      @NotNull String name,
                      @NotNull @Min(1) Long count,
                      List<Long> authorIds) implements Serializable {
}
