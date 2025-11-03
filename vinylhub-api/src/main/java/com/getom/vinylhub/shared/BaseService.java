package com.getom.vinylhub.shared;

import com.getom.vinylhub.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@NoArgsConstructor
public abstract class BaseService {

    @Autowired
    protected Validator validator;

    protected <T> void validate(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}
