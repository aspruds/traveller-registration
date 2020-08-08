package com.spruds.covid.travellerregistration.model.rest.id;

public enum IdentityDocumentType {
    PASSPORT("passport"),
    ID_CARD("id_card"),
    OTHER("other");

    private String code;

    private IdentityDocumentType(String code) {
        this.code = code;
    }
}