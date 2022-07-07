package rd.fomin.bookshop.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    private String name;
    private Long count;
    private List<Long> authorIds;
}
