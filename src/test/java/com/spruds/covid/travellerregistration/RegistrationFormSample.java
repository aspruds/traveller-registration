package com.spruds.covid.travellerregistration;

import com.spruds.covid.travellerregistration.model.rest.*;
import com.spruds.covid.travellerregistration.model.rest.address.Address;
import com.spruds.covid.travellerregistration.model.rest.id.IdentityDocument;
import com.spruds.covid.travellerregistration.model.rest.id.IdentityDocumentType;
import com.spruds.covid.travellerregistration.model.rest.transport.CarrierType;
import com.spruds.covid.travellerregistration.model.rest.transport.TransportDetails;
import com.spruds.covid.travellerregistration.model.rest.traveller.ContactInformation;
import com.spruds.covid.travellerregistration.model.rest.traveller.RecentCountry;
import com.spruds.covid.travellerregistration.model.rest.traveller.Sex;
import com.spruds.covid.travellerregistration.model.rest.traveller.Traveller;

import java.time.LocalDate;
import java.util.Collections;

public interface RegistrationFormSample {
    default RegistrationForm getSampleForm() {
        RegistrationForm form = new RegistrationForm();
        form.setDateOfEntry(LocalDate.of(2020, 8, 7));
        form.setIsTransit(false);

        TransportDetails transportDetails = new TransportDetails();
        transportDetails.setCarrierType(CarrierType.PLANE);
        transportDetails.setFlightNumber("BT101");
        transportDetails.setFlightDate(LocalDate.of(2020, 8, 6));
        form.setTransportDetails(transportDetails);

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail("john.b@gmail.com");
        contactInformation.setPhoneCountryCode("+371");
        contactInformation.setPhoneNumber("94186444");

        Traveller traveller = new Traveller();
        traveller.setFirstName("John");
        traveller.setLastName("Brown");
        traveller.setDateOfBirth(LocalDate.of(1972, 12, 29));
        traveller.setAge(48);
        traveller.setForeignerNeededForBusiness(false);
        traveller.setNationalIdNumber("721229-11302");
        traveller.setSeat("9A");
        traveller.setSex(Sex.MALE);
        traveller.setContactInformation(contactInformation);

        IdentityDocument identityDocument = new IdentityDocument();
        identityDocument.setDocumentType(IdentityDocumentType.PASSPORT);
        identityDocument.setIssuingCountryCode("LV");
        identityDocument.setDocumentNumber("PA1922112");
        traveller.setIdentityDocument(identityDocument);

        RecentCountry recentCountry = new RecentCountry();
        recentCountry.setCountryCode("LV");
        recentCountry.setDateOfExit(LocalDate.of(2020, 8, 7));
        traveller.setRecentCountries(Collections.singletonList(recentCountry));

        Address address = new Address();
        address.setCountryCode("LV");
        form.setAddresses(Collections.singletonList(address));

        form.setTravellers(Collections.singletonList(traveller));
        return form;
    }
}
