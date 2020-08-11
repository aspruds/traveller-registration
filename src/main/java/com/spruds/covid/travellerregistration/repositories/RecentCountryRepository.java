package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.traveller.RecentCountry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecentCountryRepository extends CrudRepository<RecentCountry, Long> {}
