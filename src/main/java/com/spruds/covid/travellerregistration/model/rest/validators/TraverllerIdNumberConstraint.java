package com.spruds.covid.travellerregistration.model.rest.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TravellerIdNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TraverllerIdNumberConstraint {
    String message() default "nationalIdNumber is mandatory for Latvian Residents, dateOfBirth is mandatory in all other cases";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}