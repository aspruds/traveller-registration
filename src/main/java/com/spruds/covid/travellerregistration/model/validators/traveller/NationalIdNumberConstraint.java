package com.spruds.covid.travellerregistration.model.validators.traveller;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalIdNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalIdNumberConstraint {
    String message() default "invalid nationalIdNumber";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}