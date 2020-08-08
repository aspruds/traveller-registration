package com.spruds.covid.travellerregistration;

import com.spruds.covid.travellerregistration.model.rest.RegistrationForm;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = TravellerRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubmitFormTest implements RegistrationFormSample {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testSubmitForm() throws JSONException {
        RegistrationForm form = getSampleForm();
        HttpEntity<RegistrationForm> entity = new HttpEntity<>(form, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/submit-form"),
                HttpMethod.POST, entity, String.class);

        String expected = "{transportDetails: { carrierType:PLANE, flightNumber:BT101, flightDate:2020-08-06 }," +
                "isTransit:false, dateOfEntry:2020-08-07," +
                "travellers:[{firstName:John, lastName: Brown, dateOfBirth: 1972-12-29, age: 48, " +
                "foreignerNeededForBusiness:false, nationalIdNumber: 721229-11302, seat:9A, sex: MALE," +
                "identityDocument:{documentType: PASSPORT, issuingCountryCode: LV, documentNumber: PA1922112}," +
                "contactInformation:{email: john.b@gmail.com, phoneCountryCode:'+371', phoneNumber:'94186444'}," +
                "recentCountries:[{countryCode:LV, dateOfExit: 2020-08-07}]}]}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}

