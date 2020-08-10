package com.spruds.covid.travellerregistration.controllers;

import com.spruds.covid.travellerregistration.model.rest.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RegistrationsController {
    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "https://covid-registration.gov.lv", allowCredentials = "true")
    public RegistrationForm submit(@RequestBody @Validated RegistrationForm registrationForm) {
        return registrationForm;
    }
}
