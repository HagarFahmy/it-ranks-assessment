package com.it_ranks.validation.validator;

import com.it_ranks.validation.annotaion.UpperCase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCase, String> {
    @Override
    public void initialize(UpperCase constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.equals(value.toUpperCase());

    }
}
