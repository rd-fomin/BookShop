package rd.fomin.bookshop.advice;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookRestControllerAdvice extends ResponseEntityExceptionHandler {

}
