package rd.fomin.bookshop.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rd.fomin.bookshop.model.mapper.annotation.EncodedMapping;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {
    private final PasswordEncoder passwordEncoder;

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}