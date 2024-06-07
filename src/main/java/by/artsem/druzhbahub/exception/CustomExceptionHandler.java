package by.artsem.druzhbahub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({DataNotFoundedException.class, TokenExpireException.class, DataNotCreatedException.class})
    private ResponseEntity<ErrorResponse> handlerException(RuntimeException e) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handlerException(NullPointerException e) {
        ErrorResponse ErrorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
