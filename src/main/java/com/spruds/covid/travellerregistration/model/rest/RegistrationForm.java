package com.spruds.covid.travellerregistration.model.rest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Valid
public class RegistrationForm {
    @Getter @Setter @Valid
    TransportDetails transportDetails;

    @Getter @Setter
    LocalDate dateOfEntry;

    @Getter @Setter
    Boolean isTransit;

    @Getter @Setter @Valid
    private List<Traveller> travellers;

    @Getter @Setter @NotNull @Valid
    private List<Address> addresses;
}
