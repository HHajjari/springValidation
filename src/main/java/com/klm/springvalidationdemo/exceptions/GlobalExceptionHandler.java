package com.klm.springvalidationdemo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleNoSuchElementFoundException(NoSuchElementFoundException exception) {
        return ResponseEntity
                .notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List errorList = exception.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> map = new HashMap<>();
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler
    public ResponseEntity handleJPAViolations(TransactionSystemException exception) {
        if (exception.getCause().getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException ve = (ConstraintViolationException) exception.getCause().getCause();
            List errorList = ve.getConstraintViolations().stream().map(fieldError -> {
                Map<String, String> map = new HashMap<>();
                map.put(fieldError.getPropertyPath().toString(), fieldError.getMessage());
                return map;
            }).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorList);
        }
        return ResponseEntity.badRequest().body("Database exception occured");
    }

    @ExceptionHandler
    public ResponseEntity handleJPAViolations(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

}
