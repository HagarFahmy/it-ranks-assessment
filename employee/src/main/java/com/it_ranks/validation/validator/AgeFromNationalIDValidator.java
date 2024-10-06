package com.it_ranks.validation.validator;

import com.it_ranks.validation.annotaion.ValidAgeFromNationalID;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeFromNationalIDValidator implements
        ConstraintValidator<ValidAgeFromNationalID, String> {
    @Override
    public void initialize(ValidAgeFromNationalID validAgeFromNationalID) {
        ConstraintValidator.super.initialize(validAgeFromNationalID);

    }

    @Override
    public boolean isValid(String nationalID, ConstraintValidatorContext context) {
        int year = Integer.parseInt(nationalID.substring(0, 2));
        int month = Integer.parseInt(nationalID.substring(2, 4));
        int day = Integer.parseInt(nationalID.substring(4, 6));

        LocalDate birthdate = LocalDate.of(1900 + year, month, day);
        LocalDate now = LocalDate.now();

        int age = now.minusYears(birthdate.getYear()).getYear();

        // Perform age validation based on your requirements
        return age >= 18; // For example, validate if the person is at least 18 years old
    }
}
