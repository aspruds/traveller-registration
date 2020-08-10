package com.spruds.covid.travellerregistration.model.rest.traveller;

import com.spruds.covid.travellerregistration.model.rest.id.IdentityDocument;
import com.spruds.covid.travellerregistration.model.validators.traveller.NationalIdNumberConstraint;
import com.spruds.covid.travellerregistration.model.validators.traveller.TraverllerIdNumberConstraint;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@Valid
@TraverllerIdNumberConstraint
public class Traveller {
    @NotNull
    @NotEmpty
    @Pattern(message = "only Latin characters are supported", regexp = "^([A-Za-z -']{1,})$")
    private String firstName;

    @NotNull
    @NotEmpty
    @Pattern(message = "only Latin characters are supported", regexp = "^([A-Za-z -']{1,})$")
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

    @NotEmpty
    private String seat;

    @NotNull
    private Boolean foreignerNeededForBusiness;

    @Valid  @NotNull
    private ContactInformation contactInformation;

    @Valid @NotNull
    private List<RecentCountry> recentCountries;
}
