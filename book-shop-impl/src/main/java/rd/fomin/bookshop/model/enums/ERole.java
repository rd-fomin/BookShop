package rd.fomin.bookshop.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_USER("Пользователь"),
    ROLE_MODERATOR("Модератор"),
    ROLE_ADMIN("Администратор");

    private final String explain;
}
