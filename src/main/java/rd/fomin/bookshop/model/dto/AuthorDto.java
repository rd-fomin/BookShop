package rd.fomin.bookshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class AuthorDto implements Serializable {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
}
