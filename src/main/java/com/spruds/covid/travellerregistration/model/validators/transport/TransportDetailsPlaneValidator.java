package com.spruds.covid.travellerregistration.model.validators.transport;

import com.spruds.covid.travellerregistration.model.rest.transport.CarrierType;
import com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransportDetailsPlaneValidator implements
        ConstraintValidator<TransportDetailsPlaneConstraint, TransportDetails> {
 
    @Override
    public boolean isValid(TransportDetails transportDetailsField,
      ConstraintValidatorContext cxt) {
        if(transportDetailsField != null && CarrierType.PLANE.equals(transportDetailsField.getCarrierType())) {
            return transportDetailsField.getFlightDate() != null &&
                    transportDetailsField.getFlightNumber() != null;
        } else {
            return true;
        }
    }
 
}