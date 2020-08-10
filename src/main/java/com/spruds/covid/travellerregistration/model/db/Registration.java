package com.spruds.covid.travellerregistration.model.db;

import com.spruds.covid.travellerregistration.model.db.address.Address;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "registrations")
public class Registration {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="registration_id")
    private Long registrationId;

    @Getter @Setter
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "registration_uuid", updatable = false, nullable = false)
    private UUID registrationUUID;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "registration_id")
    private Set<Address> addresses = new HashSet<Address>();

    @Getter @Setter
    @Column(name="is_transit")
    private Boolean isTransit;

    @Getter @Setter
    @Column(name="date_of_entry")
    private LocalDate dateOfEntry;

    @Getter @Setter
    @Column(name="date_received")
    private LocalDateTime dateReceived;
}
