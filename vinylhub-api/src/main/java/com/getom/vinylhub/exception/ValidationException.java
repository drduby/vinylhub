package com.getom.vinylhub.exception;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
public class ValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        this.errors = violations.stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
    }

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public ValidationException(String field, String message) {
        this.errors = Map.of(field, message);
    }
}
