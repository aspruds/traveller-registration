package com.spruds.covid.travellerregistration.services;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.db.address.Address;
import com.spruds.covid.travellerregistration.model.db.address.Country;
import com.spruds.covid.travellerregistration.model.db.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.db.traveller.ContactInformation;
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
    private Clock clock;

    private RegistrationRepository registrationRepository;
    private CountryRepository countryRepository;
    private AddressRepository addressRepository;
    private CarrierTypeRepository carrierTypeRepository;
    private TransportDetailsRepository transportDetailsRepository;
    private SexTypeRepository sexTypeRepository;
    private IdentityDocumentTypeRepository identityDocumentTypeRepository;
    private IdentityDocumentRepository identityDocumentRepository;
    private ContactInformationRepository contactInformationRepository;
    private TravellerRepository travellerRepository;

    public RegistrationService(Clock clock,
                               RegistrationRepository registrationRepository,
                               CountryRepository countryRepository,
                               AddressRepository addressRepository,
                               CarrierTypeRepository carrierTypeRepository,
                               TransportDetailsRepository transportDetailsRepository,
                               SexTypeRepository sexTypeRepository,
                               IdentityDocumentTypeRepository identityDocumentTypeRepository,
                               IdentityDocumentRepository identityDocumentRepository,
                               ContactInformationRepository contactInformationRepository,
                               TravellerRepository travellerRepository) {
        this.clock = clock;

        this.registrationRepository = registrationRepository;
        this.countryRepository = countryRepository;
        this.addressRepository = addressRepository;
        this.carrierTypeRepository = carrierTypeRepository;
        this.transportDetailsRepository = transportDetailsRepository;
        this.sexTypeRepository = sexTypeRepository;
        this.identityDocumentTypeRepository = identityDocumentTypeRepository;
        this.identityDocumentRepository = identityDocumentRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.travellerRepository = travellerRepository;
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
        setTravellers(registration, form);

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

        addressSet.stream().forEach(a -> addressRepository.save(a));
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

        transportDetailsRepository.save(transportDetails);
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

            return traveller;
        }).collect(Collectors.toSet());
        travellerSet.stream().forEach(t -> travellerRepository.save(t));
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
}
