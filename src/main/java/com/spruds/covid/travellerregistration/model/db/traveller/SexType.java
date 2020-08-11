package com.spruds.covid.travellerregistration.model.db.traveller;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sex_types")
public class SexType {
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
