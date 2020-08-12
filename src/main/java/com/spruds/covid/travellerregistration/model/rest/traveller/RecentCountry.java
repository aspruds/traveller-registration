package com.spruds.covid.travellerregistration.model.rest.traveller;

import com.spruds.covid.travellerregistration.model.validators.traveller.RecentCountryDateOfExitConstraint;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RecentCountry {
    @NotNull
    private String countryCode;

    @NotNull
    @RecentCountryDateOfExitConstraint
    private LocalDate dateOfExit;
}
