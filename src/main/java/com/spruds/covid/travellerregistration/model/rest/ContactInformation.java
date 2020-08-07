package com.spruds.covid.travellerregistration.model.rest;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class ContactInformation {
    @NotNull
    private String email;

    @NotNull
    private String phoneCountryCode;

    @NotNull
    private String phoneNumber;
}
