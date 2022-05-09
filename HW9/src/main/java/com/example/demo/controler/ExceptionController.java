package com.example.demo.controler;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Set<String>> handleError(ConstraintViolationException exception){
        Set<String> errors = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        return ResponseEntity.badRequest().body(errors);
    }
}
