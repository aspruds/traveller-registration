package com.spruds.covid.travellerregistration.model.rest.traveller;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactInformation {
    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Pattern(message = "Only + and digits are allowed", regexp = "^(\\+?[0-9]{1,30})$")
    private String phoneCountryCode;

    @NotNull
    @Pattern(message = "Only digits are allowed", regexp = "^(\\+?[0-9]{1,30})$")
    private String phoneNumber;
}
