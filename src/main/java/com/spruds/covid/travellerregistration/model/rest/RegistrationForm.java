package com.spruds.covid.travellerregistration.model.rest;

import com.spruds.covid.travellerregistration.model.rest.address.Address;
import com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.rest.traveller.Traveller;
import com.spruds.covid.travellerregistration.model.validators.DateOfEntryConstraint;
import com.spruds.covid.travellerregistration.model.validators.transport.TransportDetailsPlaneConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Valid
public class RegistrationForm {
    @Getter @Setter @Valid @NotNull @TransportDetailsPlaneConstraint
    TransportDetails transportDetails;

    @Getter @Setter @NotNull @DateOfEntryConstraint
    LocalDate dateOfEntry;

    @Getter @Setter @NotNull
    Boolean isTransit;

    @Getter @Setter @Valid @NotNull
    private List<Traveller> travellers;

    @Getter @Setter @Valid @NotNull
    private List<Address> addresses;

    @Getter @Setter
    private LocalDateTime dateReceived;

    @Getter @Setter
    private UUID registrationId;
}
