package com.spruds.covid.travellerregistration.model.rest.traveller;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RecentCountry {
    private String countryCode;

    @NotNull
    private LocalDate dateOfExit;
}
