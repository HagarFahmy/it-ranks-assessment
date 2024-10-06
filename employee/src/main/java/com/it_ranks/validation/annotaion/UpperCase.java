package com.it_ranks.validation.annotaion;


import com.it_ranks.validation.validator.UpperCaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UpperCaseValidator.class)
public @interface UpperCase {
    String message() default "{uppercase.name}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
