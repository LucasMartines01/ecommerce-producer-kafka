package com.martinesco.br.kafka.example.domain.exceptions;

import org.springframework.validation.FieldError;

public record FieldException(String field, String message) {
    public FieldException(FieldError err){
        this(err.getField(), err.getDefaultMessage());
    }
}
