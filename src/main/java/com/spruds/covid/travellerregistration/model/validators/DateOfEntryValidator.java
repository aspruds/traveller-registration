package com.spruds.covid.travellerregistration.model.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;

@Slf4j
public class DateOfEntryValidator implements
        ConstraintValidator<DateOfEntryConstraint, LocalDate> {

    private Clock clock;

    public DateOfEntryValidator(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean isValid(LocalDate dateOfExit, ConstraintValidatorContext cxt) {
        if(dateOfExit != null) {
            LocalDate now = LocalDate.now(clock);
            return dateOfExit.isAfter(now.minusDays(15)) && dateOfExit.isBefore(now.plusDays(3));
        } else {
            // valid if null
            return true;
        }
    }
}