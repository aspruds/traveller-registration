package com.spruds.covid.travellerregistration.model.db.traveller;

import com.spruds.covid.travellerregistration.model.db.address.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "recent_countries")
public class RecentCountry {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="recent_country_id")
    private Long recentCountryId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "traveller_id")
    private Traveller traveller;

    @Getter
    @Setter
    @Column(name = "date_of_exit")
    LocalDate dateOfExit;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
