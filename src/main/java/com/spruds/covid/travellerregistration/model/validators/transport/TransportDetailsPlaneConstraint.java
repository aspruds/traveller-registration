package com.spruds.covid.travellerregistration.model.validators.transport;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TransportDetailsPlaneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransportDetailsPlaneConstraint {
    String message() default "flightNumber and flightDate required if carrierTypeCode is plane";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}