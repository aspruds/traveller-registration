package com.spruds.covid.travellerregistration.model.db.traveller;

import com.spruds.covid.travellerregistration.model.db.Registration;
import com.spruds.covid.travellerregistration.model.db.id.IdentityDocument;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "travellers")
public class Traveller {
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="traveller_id")
    private Long travellerId;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sex_type_id")
    private SexType sex;

    @Setter
    @Getter
    @NotNull
    @Column(name="first_name")
    private String firstName;

    @Setter
    @Getter
    @NotNull
    @Column(name="last_name")
    private String lastName;

    @Setter
    @Getter
    @Column(name="national_id_number")
    private String nationalIdNumber;

    @Setter
    @Getter
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Setter
    @Getter
    @Column(name="age")
    private Integer age;

    @Setter
    @Getter
    @Column(name="seat")
    private String seat;

    @Setter
    @Getter
    @Column(name="foreigner_needed_for_business")
    private Boolean foreignerNeededForBusiness;

    @Getter
    @Setter
    @NotNull
    @OneToOne(mappedBy = "traveller", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ContactInformation contactInformation;

    @Getter
    @Setter
    @NotNull
    @OneToOne(mappedBy = "traveller", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private IdentityDocument identityDocument;

    @Getter
    @Setter
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "traveller_id")
    private Set<RecentCountry> recentCountries = new HashSet<>();
}
