package com.spruds.covid.travellerregistration.model.db.traveller;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact_information")
public class ContactInformation {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="contact_information_id")
    private Long contactInformationId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    private Traveller traveller;

    @Getter
    @Setter
    @NotNull
    @Column(name="email")
    private String email;

    @Setter
    @Getter
    @NotNull
    @Column(name="phone_country_code")
    private String phoneCountryCode;

    @Setter
    @Getter
    @NotNull
    @Column(name="phone")
    private String phoneNumber;
}
