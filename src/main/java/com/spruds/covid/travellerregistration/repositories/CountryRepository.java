package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.address.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    public Optional<Country> findByCode(String code);
}
