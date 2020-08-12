package com.spruds.covid.travellerregistration.model.db.id;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "identity_document_types")
public class IdentityDocumentType {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="type_id")
    private Long typeId;

    @Setter
    @Getter
    @Column(name="code")
    private String code;

    @Setter
    @Getter
    @Column(name="name")
    private String name;
}
