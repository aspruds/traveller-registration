package com.spruds.covid.travellerregistration.model.rest.address;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Address {
    @NotNull
    private String countryCode;
    private String zip;
    private String province;
    private String city;
    private String district;
    private String village;
    private String street;
    private String house;
    private String flat;
}
