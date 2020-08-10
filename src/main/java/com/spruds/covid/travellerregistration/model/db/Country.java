package com.spruds.covid.travellerregistration.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Country {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="country_id")
    private Long countryId;

    @Setter
    @Getter
    @Column(name="code")
    private String code;

    @Setter
    @Getter
    @Column(name="name")
    private String name;

    @Getter @Setter
    private Boolean isolationRequired;
}
