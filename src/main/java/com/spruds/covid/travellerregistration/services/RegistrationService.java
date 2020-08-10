package com.spruds.covid.travellerregistration.services;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.rest.RegistrationForm;
import com.spruds.covid.travellerregistration.repositories.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationService {
    private Clock clock;

    private RegistrationRepository registrationRepository;

    public RegistrationService(Clock clock, RegistrationRepository registrationRepository) {
        this.clock = clock;
        this.registrationRepository = registrationRepository;
    }

    public Registration save(RegistrationForm form) {
        LocalDateTime now = LocalDateTime.now(clock);

        Registration registration = new Registration();
        registration.setRegistrationUUID(UUID.randomUUID());
        registration.setDateReceived(now);
        registration.setDateOfEntry(form.getDateOfEntry());
        registration.setIsTransit(form.getIsTransit());

        registrationRepository.save(registration);

        return registration;
    }
}
