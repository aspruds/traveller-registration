package com.spruds.covid.travellerregistration.model.db.transport;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.db.transport.CarrierType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transport_details")
public class TransportDetails {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="transport_details_id")
    private Long transportDetailsId;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "carrier_type_id")
    private CarrierType carrierType;

    @Setter
    @Getter
    @Column(name="flight_number")
    private String flightNumber;

    @Setter
    @Getter
    @Column(name="flight_date")
    private LocalDate flightDate;
}
