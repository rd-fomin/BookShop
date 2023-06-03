package rd.fomin.bookshop.model.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record LoginRequest(String username,
                           String password) implements Serializable {
}
