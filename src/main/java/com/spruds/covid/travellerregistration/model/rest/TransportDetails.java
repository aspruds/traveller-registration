package com.spruds.covid.travellerregistration.model.rest;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class TransportDetails {
    @Getter
    @Setter
    @NotNull
    private String carrierTypeCode;

    @Getter
    @Setter
    private String flightNumber;

    @Getter
    @Setter
    private LocalDate flightDate;
}
