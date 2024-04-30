package com.martinesco.br.kafka.example.configurations;

import com.martinesco.br.kafka.example.domain.exceptions.BusinessException;
import com.martinesco.br.kafka.example.domain.exceptions.FieldException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity fieldError(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldException::new).toList());
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity entityExists(EntityExistsException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity genericError(Exception ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessError(BusinessException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
