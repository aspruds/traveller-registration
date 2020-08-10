package com.spruds.covid.travellerregistration.model.validators.traveller;

import com.spruds.covid.travellerregistration.RegistrationFormSample;
import com.spruds.covid.travellerregistration.model.validators.traveller.NationalIdNumberValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdNumberValidatorTest implements RegistrationFormSample {
    private NationalIdNumberValidator validator =
            new NationalIdNumberValidator();

    @Nested
    class TIdNumberValidatorValidFlow {
        @Test
        void validNationalIdNumber() {
            assertTrue(isValid("111111-11111"));
        }

        @Test
        void validNationalIdNumberWithoutDash() {
            assertTrue(isValid("11111111111"));
        }
    }

    @Nested
    class IdNumberValidatorInvalidFlow {
        @Test
        void numberWithSpace() {
            assertFalse(isValid("111111 11111"));
        }

        @Test
        void characters() {
            assertFalse(isValid("2A1072-11301"));
        }
    }

    private boolean isValid(String value) {
        return validator.isValid(value, null);
    }
}

