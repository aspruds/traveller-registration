package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.traveller.Traveller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravellerRepository extends CrudRepository<Traveller, Long> {}
