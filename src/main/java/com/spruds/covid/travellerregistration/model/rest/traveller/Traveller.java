package com.spruds.covid.travellerregistration.model.rest.traveller;

import com.spruds.covid.travellerregistration.model.rest.id.IdentityDocument;
import com.spruds.covid.travellerregistration.model.rest.validators.NationalIdNumberConstraint;
import com.spruds.covid.travellerregistration.model.rest.validators.TraverllerIdNumberConstraint;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Valid
@TraverllerIdNumberConstraint
public class Traveller {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Valid @NotNull
    private IdentityDocument identityDocument;

    @NationalIdNumberConstraint
    private String nationalIdNumber;

    private LocalDate dateOfBirth;

    @NotNull
    private Sex sex;

    @NotNull
    private Integer age;

    private String seat;

    @NotNull
    private Boolean foreignerNeededForBusiness;

    @Valid  @NotNull
    private ContactInformation contactInformation;

    @Valid @NotNull
    private List<RecentCountry> recentCountries;
}
