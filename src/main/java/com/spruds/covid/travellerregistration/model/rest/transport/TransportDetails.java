package com.spruds.covid.travellerregistration.model.rest.transport;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TransportDetails {
    @Getter
    @Setter
    @NotNull
    private CarrierType carrierType;

    @Getter
    @Setter
    private String flightNumber;

    @Getter
    @Setter
    private LocalDate flightDate;
}
