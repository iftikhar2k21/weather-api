package com.terminus.sample.handler;

import com.terminus.sample.exceptions.InvalidCityNameOrCountryCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestController
@ControllerAdvice
@Slf4j


public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(" ", exception);
        var errorDetails = new ErrorDetails("Invalid Request", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException exception) {
        log.error(" ", exception);
        var errorDetails = new ErrorDetails("System Error", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(InvalidCityNameOrCountryCodeException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCityNameOrCountryCode(InvalidCityNameOrCountryCodeException ex) {
        log.error("", ex);
        var errorDetails = new ErrorDetails(ex.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
