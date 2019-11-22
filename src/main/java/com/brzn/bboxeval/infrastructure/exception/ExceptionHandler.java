package com.brzn.bboxeval.infrastructure.exception;

import com.brzn.bboxeval.box.exception.BoxNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BoxNotFoundException.class)
    ResponseEntity<ErrorMessage> handleBoxNotFound(BoxNotFoundException ex) {
        ErrorMessage message = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    }
}
