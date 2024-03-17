package com.example.lyubishevtime.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Data> handleApiException(ApiException ex) {
        Data data = new Data(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(data);
    }

    public record Data(String errorMessage) {
    }
}