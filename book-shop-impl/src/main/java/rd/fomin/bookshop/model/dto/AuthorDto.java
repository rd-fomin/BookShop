package rd.fomin.bookshop.model.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
public record AuthorDto(Long id,
                        @NotNull @NotBlank String firstName,
                        String middleName,
                        @NotNull @NotBlank String lastName) implements Serializable {
}
