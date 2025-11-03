package com.getom.vinylhub.shared;

import com.getom.vinylhub.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public class ValidationUtils {
    public static <T> void validate(Validator validator, T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
