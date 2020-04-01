package tiv.exchange.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ExchangeResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFound.class)
    public final ResponseEntity<ExceptionResponse> handleAccountNotFound(final Exception ex, final WebRequest request) {
        final ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrencyNotFound.class)
    public final ResponseEntity<ExceptionResponse> handleCurrencyNotFound(final Exception ex, final WebRequest request) {
        final ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidAccountID(final Exception ex, final WebRequest request) {
        final ExceptionResponse exResponse = new ExceptionResponse(LocalDateTime.now(), "The account id format is invalid.", request.getDescription(false));
        return new ResponseEntity(exResponse, HttpStatus.BAD_REQUEST);
    }

}
