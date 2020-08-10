package com.spruds.covid.travellerregistration.model.validators.traveller;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Clock;
import java.time.LocalDate;

public class RecentCountryDateOfExitValidator implements
        ConstraintValidator<RecentCountryDateOfExitConstraint, LocalDate> {

    private Clock clock;

    public RecentCountryDateOfExitValidator(Clock clock) {
        this.clock = clock;
    }

    @Override
    public boolean isValid(LocalDate dateOfExit, ConstraintValidatorContext cxt) {
        if(dateOfExit != null) {
            LocalDate now = LocalDate.now(clock);
            return dateOfExit.isAfter(now.minusDays(15)) && dateOfExit.isBefore(now.plusDays(1));
        } else {
            // valid if null
            return true;
        }
    }
}