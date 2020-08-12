package com.spruds.covid.travellerregistration.services;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.db.address.Address;
import com.spruds.covid.travellerregistration.model.db.address.Country;
import com.spruds.covid.travellerregistration.model.db.id.IdentityDocumentType;
import com.spruds.covid.travellerregistration.model.db.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.db.traveller.ContactInformation;
import com.spruds.covid.travellerregistration.model.db.id.IdentityDocument;
import com.spruds.covid.travellerregistration.model.db.traveller.RecentCountry;
import com.spruds.covid.travellerregistration.model.db.traveller.SexType;
import com.spruds.covid.travellerregistration.model.db.traveller.Traveller;
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
    private final Clock clock;

    private final RegistrationRepository registrationRepository;
    private final CountryRepository countryRepository;
    private final CarrierTypeRepository carrierTypeRepository;
    private final SexTypeRepository sexTypeRepository;
    private final IdentityDocumentTypeRepository identityDocumentTypeRepository;

    public RegistrationService(Clock clock,
                               RegistrationRepository registrationRepository,
                               CountryRepository countryRepository,
                               CarrierTypeRepository carrierTypeRepository,
                               SexTypeRepository sexTypeRepository,
                               IdentityDocumentTypeRepository identityDocumentTypeRepository) {
        this.clock = clock;

        this.registrationRepository = registrationRepository;
        this.countryRepository = countryRepository;
        this.carrierTypeRepository = carrierTypeRepository;
        this.sexTypeRepository = sexTypeRepository;
        this.identityDocumentTypeRepository = identityDocumentTypeRepository;
    }

    public Registration save(RegistrationForm form) {
        LocalDateTime now = LocalDateTime.now(clock);

        Registration registration = new Registration();
        registration.setRegistrationUUID(UUID.randomUUID());
        registration.setDateReceived(now);
        registration.setDateOfEntry(form.getDateOfEntry());
        registration.setIsTransit(form.getIsTransit());

        setTransportDetails(registration, form);
        setAddresses(registration, form);
        setTravellers(registration, form);

        registrationRepository.save(registration);
        return registration;
    }

    private void setAddresses(Registration registration, RegistrationForm form) {
        Set<Address> addressSet = form.getAddresses().stream().map( a -> {
            Country country = countryByCode(a.getCountryCode());

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
        registration.setAddresses(addressSet);
    }

    private void setTransportDetails(Registration registration, RegistrationForm form) {
        com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails
                transportDetailsRest = form.getTransportDetails();

        CarrierType carrierType = carrierTypeByCode(transportDetailsRest.getCarrierType().name());

        TransportDetails transportDetails = new TransportDetails();
        transportDetails.setRegistration(registration);
        transportDetails.setCarrierType(carrierType);
        transportDetails.setFlightDate(transportDetailsRest.getFlightDate());
        transportDetails.setFlightNumber(transportDetailsRest.getFlightNumber());
        registration.setTransportDetails(transportDetails);
    }

    private void setTravellers(Registration registration, RegistrationForm form) {
        Set<Traveller> travellerSet = form.getTravellers().stream().map(t -> {
            SexType sex = sexByCode(t.getSex().name());

            Traveller traveller = new Traveller();
            traveller.setRegistration(registration);
            traveller.setFirstName(t.getFirstName());
            traveller.setLastName(t.getLastName());
            traveller.setAge(t.getAge());
            traveller.setNationalIdNumber(t.getNationalIdNumber());
            traveller.setDateOfBirth(t.getDateOfBirth());
            traveller.setForeignerNeededForBusiness(t.getForeignerNeededForBusiness());
            traveller.setSeat(t.getSeat());
            traveller.setSex(sex);

            // contact information
            ContactInformation contactInformation = new ContactInformation();
            com.spruds.covid.travellerregistration.model.rest.traveller.ContactInformation
                    contactInformationRest = t.getContactInformation();

            contactInformation.setEmail(contactInformationRest.getEmail());
            contactInformation.setPhoneCountryCode(contactInformationRest.getPhoneCountryCode());
            contactInformation.setPhoneNumber(contactInformationRest.getPhoneNumber());
            contactInformation.setTraveller(traveller);
            traveller.setContactInformation(contactInformation);

            // identity document
            IdentityDocument identityDocument = new IdentityDocument();
            com.spruds.covid.travellerregistration.model.rest.id.IdentityDocument
                    identityDocumentRest = t.getIdentityDocument();
            identityDocument.setDocumentNumber(identityDocumentRest.getDocumentNumber());

            Country issuingCountry = countryByCode(identityDocumentRest.getIssuingCountryCode());
            identityDocument.setIssuingCountry(issuingCountry);

            IdentityDocumentType identityDocumentType =
                    identityDocumentTypeByCode(identityDocumentRest.getDocumentType().name());
            identityDocument.setDocumentType(identityDocumentType);
            identityDocument.setTraveller(traveller);
            traveller.setIdentityDocument(identityDocument);

            // recent countries
            Set<RecentCountry> recentCountrySet = t.getRecentCountries().stream().map(c -> {
                Country recentCountryDAO = countryByCode(c.getCountryCode());
                RecentCountry recentCountry = new RecentCountry();
                recentCountry.setTraveller(traveller);
                recentCountry.setCountry(recentCountryDAO);
                recentCountry.setDateOfExit(c.getDateOfExit());
                return recentCountry;
            }).collect(Collectors.toSet());
            traveller.setRecentCountries(recentCountrySet);
            return traveller;
        }).collect(Collectors.toSet());
        registration.setTravellers(travellerSet);
    }

    private Country countryByCode(String code) {
        return countryRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("unable to find country by code: " + code));
    }

    private SexType sexByCode(String code) {
        return sexTypeRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("unable to find sex type by code: " + code));
    }

    private CarrierType carrierTypeByCode(String code) {
        return carrierTypeRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("could not find carrier type by code: " +  code));
    }

    private IdentityDocumentType identityDocumentTypeByCode(String code) {
        return identityDocumentTypeRepository
                .findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("could not find identity document type by code: " +  code));
    }
}
