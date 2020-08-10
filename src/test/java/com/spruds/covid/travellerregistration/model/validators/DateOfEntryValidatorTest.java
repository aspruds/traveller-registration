package com.spruds.covid.travellerregistration.model.validators;

import com.spruds.covid.travellerregistration.config.MockClockConfiguration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateOfEntryValidatorTest {
    private DateOfEntryValidator validator;

    private Clock clock;

    public DateOfEntryValidatorTest() {
        MockClockConfiguration clockConfiguration = new MockClockConfiguration();
        clock = clockConfiguration.fixedClock();

        validator = new DateOfEntryValidator(clock);
    }

    @Nested
    class ValidFlow {
        @Test
        void validDateOfEntryPast() {
            LocalDate past = LocalDate.now(clock).minusDays(14);
            assertTrue(isValid(past));
        }

        @Test
        void validDateOfEntryPresent() {
            LocalDate past = LocalDate.now(clock);
            assertTrue(isValid(past));
        }

        @Test
        void validDateOfEntryFuture() {
            LocalDate past = LocalDate.now(clock).plusDays(2);
            assertTrue(isValid(past));
        }
    }

    @Nested
    class InvalidFlow {
        @Test
        void validDateOfEntryPast() {
            LocalDate past = LocalDate.now(clock).minusDays(15);
            assertFalse(isValid(past));
        }

        @Test
        void validDateOfEntryFuture() {
            LocalDate past = LocalDate.now(clock).plusDays(3);
            assertFalse(isValid(past));
        }
    }

    private boolean isValid(LocalDate value) {
        return validator.isValid(value, null);
    }
}

