package com.spruds.covid.travellerregistration.model.db.address;

import com.spruds.covid.travellerregistration.model.db.Registration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="address_id")
    private Long addressId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Getter @Setter
    @Column(name = "zip")
    private String zip;

    @Getter @Setter
    @Column(name = "province")
    private String province;

    @Getter @Setter
    @Column(name = "city")
    private String city;

    @Getter @Setter
    @Column(name = "district")
    private String district;

    @Getter @Setter
    @Column(name = "village")
    private String village;

    @Getter @Setter
    @Column(name = "street")
    private String street;

    @Getter @Setter
    @Column(name = "house")
    private String house;

    @Getter @Setter
    @Column(name = "flat")
    private String flat;
}
