package com.spruds.covid.travellerregistration.model.validators.traveller;

import com.spruds.covid.travellerregistration.RegistrationFormSample;
import com.spruds.covid.travellerregistration.model.rest.traveller.Traveller;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravellerIdNumberValidatorTest implements RegistrationFormSample {
    private TravellerIdNumberValidator validator =
            new TravellerIdNumberValidator();

    @Nested
    class TravellerIdNumberValidatorValidFlow {
        @Test
        void travellerWithNationalId() {
            Traveller traveller = getTraveller();
            traveller.setNationalIdNumber("261072-11301");
            traveller.setDateOfBirth(null);
            traveller.getIdentityDocument().setIssuingCountryCode("LV");
            assertTrue(isValid(traveller));
        }

        @Test
        void travellerWithoutNationalId() {
            Traveller traveller = getTraveller();
            traveller.setDateOfBirth(LocalDate.now());
            traveller.getIdentityDocument().setIssuingCountryCode("EE");
            assertTrue(isValid(traveller));
        }
    }

    @Nested
    class TravellerIdNumberValidatorInvalidFlow {
        @Test
        void travellerWithNationalId() {
            Traveller traveller = getTraveller();
            traveller.setNationalIdNumber(null);
            traveller.setDateOfBirth(LocalDate.now());
            traveller.getIdentityDocument().setIssuingCountryCode("LV");
            assertFalse(isValid(traveller));
        }

        @Test
        void travellerWithoutNationalId() {
            Traveller traveller = getTraveller();
            traveller.setNationalIdNumber("261072-11301");
            traveller.setDateOfBirth(null);
            traveller.getIdentityDocument().setIssuingCountryCode("EE");
            assertFalse(isValid(traveller));
        }
    }

    private Traveller getTraveller() {
        return getSampleForm().getTravellers().get(0);
    }

    private boolean isValid(Traveller value) {
        return validator.isValid(value, null);
    }
}

