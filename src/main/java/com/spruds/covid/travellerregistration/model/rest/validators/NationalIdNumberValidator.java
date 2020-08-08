package com.spruds.covid.travellerregistration.model.rest.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalIdNumberValidator implements
        ConstraintValidator<NationalIdNumberConstraint, String> {
 
    @Override
    public boolean isValid(String nationalIdNumber, ConstraintValidatorContext cxt) {
        if(nationalIdNumber != null) {
            return nationalIdNumber.matches("^([0-9]{6})(-{0,1})([0-9]{5})$");
        } else {
            // valid if null
            return true;
        }
    }
}