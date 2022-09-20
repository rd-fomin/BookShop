package rd.fomin.bookshop.controller;

import static org.springframework.http.ResponseEntity.ok;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rd.fomin.bookshop.model.dto.MainPageDto;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    @CrossOrigin
    @GetMapping
    public ResponseEntity<MainPageDto> get() {
        return ok(new MainPageDto("Добро пожаловать в лучший магазин книг в России!"));
    }
}
