package com.spruds.covid.travellerregistration.model.rest.traveller;

public enum Sex {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String code;

    private Sex(String code) {
        this.code = code;
    }
}