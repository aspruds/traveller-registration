package com.spruds.covid.travellerregistration.model.rest.transport;

public enum CarrierType {
    PLANE("plane"),
    SHIP("ship"),
    BUS("bus"),
    CAR("car"),
    WALKING("walking");
 
    private String code;
 
    private CarrierType(String code) {
        this.code = code;
    }
}