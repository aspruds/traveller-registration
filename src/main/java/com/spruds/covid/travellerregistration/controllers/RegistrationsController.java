package com.spruds.covid.travellerregistration.controllers;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.rest.RegistrationForm;
import com.spruds.covid.travellerregistration.services.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDateTime;

@RestController
@Slf4j
public class RegistrationsController {
    private Clock clock;
    private RegistrationService registrationService;

    public RegistrationsController(Clock clock, RegistrationService registrationService) {
        this.clock = clock;
        this.registrationService = registrationService;
    }

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(origins = "https://covid-registration.gov.lv", allowCredentials = "true")
    public RegistrationForm submit(@RequestBody @Validated RegistrationForm registrationForm) {
        Registration savedRegistration = registrationService.save(registrationForm);
        registrationForm.setDateReceived(savedRegistration.getDateReceived());
        registrationForm.setRegistrationId(savedRegistration.getRegistrationUUID());
        return registrationForm;
    }
}
