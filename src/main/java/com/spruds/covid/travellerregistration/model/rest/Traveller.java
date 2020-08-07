package com.spruds.covid.travellerregistration.model.rest;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Valid
public class Traveller {
    private String firstName;
    private String lastName;
    private String nationalIdNumber;
    private LocalDate dateOfBirth;
    private String sexTypeCode;
    private Integer age;
    private String seat;
    private Boolean foreignerNeededForBusiness;

    @Valid
    private IdentityDocument identityDocument;

    @Valid
    private ContactInformation contactInformation;

    @NotNull @Valid
    private List<RecentCountry> recentCountries;
}
