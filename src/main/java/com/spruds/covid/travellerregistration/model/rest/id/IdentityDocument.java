package com.spruds.covid.travellerregistration.model.rest.id;

import lombok.Data;

@Data
public class IdentityDocument {
    private IdentityDocumentType documentType;
    private String issuingCountryCode;
    private String documentNumber;
}
