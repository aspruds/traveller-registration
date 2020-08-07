package com.spruds.covid.travellerregistration.model.rest;

import lombok.Data;

@Data
public class IdentityDocument {
    private String identityDocumentTypeCode;
    private String identityDocumentCitizenshipCountryCode;
    private String identityDocumentNumber;
}
