package com.spruds.covid.travellerregistration.model.db.address;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
public class Country {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="country_id")
    private Long countryId;

    @Setter
    @Getter
    @NotNull
    @Column(name="code")
    private String code;

    @Setter
    @Getter
    @NotNull
    @Column(name="name")
    private String name;

    @Getter
    @Setter
    @NotNull
    @Column(name = "isolation_required")
    private Boolean isolationRequired;
}
