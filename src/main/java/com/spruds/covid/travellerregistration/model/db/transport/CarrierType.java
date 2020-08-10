package com.spruds.covid.travellerregistration.model.db.transport;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class CarrierType {
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
