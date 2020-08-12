package com.spruds.covid.travellerregistration.model.db.id;

import com.spruds.covid.travellerregistration.model.db.address.Country;
import com.spruds.covid.travellerregistration.model.db.traveller.SexType;
import com.spruds.covid.travellerregistration.model.db.traveller.Traveller;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "identity_documents")
public class IdentityDocument {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="identity_document_id")
    private Long contactInformationId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    private Traveller traveller;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "issuing_country_id")
    private Country issuingCountry;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "identity_document_type_id")
    private IdentityDocumentType documentType;

    @Setter
    @Getter
    @NotNull
    @Column(name="document_number")
    private String documentNumber;
}
