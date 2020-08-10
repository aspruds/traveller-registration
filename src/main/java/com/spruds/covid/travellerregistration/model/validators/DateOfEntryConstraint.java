package com.spruds.covid.travellerregistration.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateOfEntryValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfEntryConstraint {
    String message() default "dateOfExit must be within the last 14 days";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}