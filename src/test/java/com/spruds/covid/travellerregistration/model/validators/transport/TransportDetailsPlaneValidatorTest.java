package com.spruds.covid.travellerregistration.model.validators.transport;

import com.spruds.covid.travellerregistration.RegistrationFormSample;
import com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.validators.transport.TransportDetailsPlaneValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import com.spruds.covid.travellerregistration.model.rest.transport.CarrierType;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransportDetailsPlaneValidatorTest implements RegistrationFormSample {
    private TransportDetailsPlaneValidator validator =
            new TransportDetailsPlaneValidator();

    @Nested
    class TransportDetailsPlaneValidatorValidFlow {
        @Test
        void planeWithDetails() {
            TransportDetails details = new TransportDetails();
            details.setCarrierType(CarrierType.PLANE);
            details.setFlightDate(LocalDate.now());
            details.setFlightNumber("BT101");

            assertTrue(isValid(details));
        }

        @Test
        void shipWithoutDetails() {
            TransportDetails details = new TransportDetails();
            details.setCarrierType(CarrierType.SHIP);
            assertTrue(isValid(details));
        }
    }

    @Nested
    class TransportDetailsPlaneValidatorInvalidFlow {
        @Test
        void planeWithoutFlightDate() {
            TransportDetails details = new TransportDetails();
            details.setCarrierType(CarrierType.PLANE);
            details.setFlightNumber("BT101");

            assertFalse(isValid(details));
        }

        @Test
        void planeWithoutFlightNumber() {
            TransportDetails details = new TransportDetails();
            details.setCarrierType(CarrierType.PLANE);
            details.setFlightNumber("BT101");
            assertFalse(isValid(details));
        }
    }

    private boolean isValid(TransportDetails value) {
        return validator.isValid(value, null);
    }
}

