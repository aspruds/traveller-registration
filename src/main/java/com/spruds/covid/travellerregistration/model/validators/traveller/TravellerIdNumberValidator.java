package com.spruds.covid.travellerregistration.model.validators.traveller;

import com.spruds.covid.travellerregistration.model.rest.id.IdentityDocument;
import com.spruds.covid.travellerregistration.model.rest.traveller.Traveller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TravellerIdNumberValidator implements
        ConstraintValidator<TraverllerIdNumberConstraint, Traveller> {
 
    @Override
    public boolean isValid(Traveller traveller, ConstraintValidatorContext cxt) {
        if(traveller != null && traveller.getIdentityDocument() != null) {
            IdentityDocument identityDocument = traveller.getIdentityDocument();
            if("LV".equals(identityDocument.getIssuingCountryCode())) {
                return traveller.getNationalIdNumber() != null;
            } else {
                return traveller.getDateOfBirth() != null;
            }
        } else {
            return false;
        }
    }
 
}