package com.spruds.covid.travellerregistration;

import com.spruds.covid.travellerregistration.model.rest.*;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest(classes = TravellerRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubmitFormTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testSubmitForm() throws JSONException {

        RegistrationForm form = new RegistrationForm();
        form.setDateOfEntry(LocalDate.of(2020, 8, 7));
        form.setIsTransit(false);

        TransportDetails transportDetails = new TransportDetails();
        transportDetails.setCarrierTypeCode("plane");
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
        traveller.setSexTypeCode("M");
        traveller.setContactInformation(contactInformation);

        RecentCountry recentCountry = new RecentCountry();
        recentCountry.setCountryCode("LV");
        recentCountry.setDateOfExit(LocalDate.of(2020, 8, 7));
        traveller.setRecentCountries(Collections.singletonList(recentCountry));

        Address address = new Address();
        address.setCountryCode("LV");
        form.setAddresses(Collections.singletonList(address));

        form.setTravellers(Collections.singletonList(traveller));

        HttpEntity<RegistrationForm> entity = new HttpEntity<>(form, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/submit-form"),
                HttpMethod.POST, entity, String.class);

        String expected = "{transportDetails: { carrierTypeCode:plane, flightNumber:BT101, flightDate:2020-08-06 }," +
                "isTransit:false, dateOfEntry:2020-08-07," +
                "travellers:[{firstName:John, lastName: Brown, dateOfBirth: 1972-12-29, age: 48, " +
                "foreignerNeededForBusiness:false, nationalIdNumber: 721229-11302, seat:9A, sexTypeCode: M," +
                " contactInformation: {email: john.b@gmail.com, phoneCountryCode:'+371', phoneNumber:'94186444'}}]}";
        System.out.println("resp:" + response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}

