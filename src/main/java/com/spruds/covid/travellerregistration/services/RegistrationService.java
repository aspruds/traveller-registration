package com.spruds.covid.travellerregistration.services;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.db.address.Address;
import com.spruds.covid.travellerregistration.model.db.address.Country;
import com.spruds.covid.travellerregistration.model.db.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.rest.RegistrationForm;
import com.spruds.covid.travellerregistration.model.db.transport.CarrierType;
import com.spruds.covid.travellerregistration.repositories.*;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrationService {
    private Clock clock;

    private RegistrationRepository registrationRepository;
    private CountryRepository countryRepository;
    private AddressRepository addressRepository;
    private CarrierTypeRepository carrierTypeRepository;
    private TransportDetailsRepository transportDetailsRepository;

    public RegistrationService(Clock clock,
                               RegistrationRepository registrationRepository,
                               CountryRepository countryRepository,
                               AddressRepository addressRepository,
                               CarrierTypeRepository carrierTypeRepository,
                               TransportDetailsRepository transportDetailsRepository) {
        this.clock = clock;
        this.registrationRepository = registrationRepository;
        this.countryRepository = countryRepository;
        this.addressRepository = addressRepository;
        this.carrierTypeRepository = carrierTypeRepository;
        this.transportDetailsRepository = transportDetailsRepository;
    }

    public Registration save(RegistrationForm form) {
        LocalDateTime now = LocalDateTime.now(clock);

        Registration registration = new Registration();
        registration.setRegistrationUUID(UUID.randomUUID());
        registration.setDateReceived(now);
        registration.setDateOfEntry(form.getDateOfEntry());
        registration.setIsTransit(form.getIsTransit());

        registrationRepository.save(registration);
        setTransportDetails(registration, form);
        setAddresses(registration, form);

        return registration;
    }

    private void setAddresses(Registration registration, RegistrationForm form) {
        Set<Address> addressSet = form.getAddresses().stream().map( a -> {
            Country country = countryRepository
                    .findByCode(a.getCountryCode())
                    .orElseThrow(() -> new EntityNotFoundException("unable to find country by code: " + a.getCountryCode()));

            Address address = new Address();
            address.setRegistration(registration);
            address.setCountry(country);
            address.setZip(a.getZip());
            address.setProvince(a.getProvince());
            address.setCity(a.getCity());
            address.setDistrict(a.getDistrict());
            address.setVillage(a.getVillage());
            address.setStreet(a.getStreet());
            address.setHouse(a.getHouse());
            address.setFlat(a.getFlat());
            return address;
        }).collect(Collectors.toSet());

        addressSet.stream().forEach(a -> addressRepository.save(a));
        registration.setAddresses(addressSet);
    }

    private void setTransportDetails(Registration registration, RegistrationForm form) {
        com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails transportDetailsRest =
                form.getTransportDetails();
        CarrierType carrierType = carrierTypeRepository
                .findByCode(transportDetailsRest.getCarrierType().name())
                .orElseThrow(() -> new EntityNotFoundException("could not find carrier type by code: " +  form.getTransportDetails().getCarrierType().name()));
        TransportDetails transportDetails = new TransportDetails();
        transportDetails.setRegistration(registration);
        transportDetails.setCarrierType(carrierType);
        transportDetails.setFlightDate(transportDetailsRest.getFlightDate());
        transportDetails.setFlightNumber(transportDetailsRest.getFlightNumber());

        transportDetailsRepository.save(transportDetails);
    }
}
