package rd.fomin.bookshop.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.dto.user.UserCreateDto;
import rd.fomin.bookshop.model.dto.user.UserInfoDto;
import rd.fomin.bookshop.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInfoDto>> getAll() {
        return ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getOne(@PathVariable Long id) {
        return ok(userService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<UserInfoDto> create(@RequestBody @Validated UserCreateDto userToCreate,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return unprocessableEntity().build();
        }
        var savedUser = userService.create(userToCreate);
        return ok(savedUser);
    }
}
