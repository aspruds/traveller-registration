package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.address.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    public Country findFirstByCode(String code);
}
