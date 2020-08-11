package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.transport.TransportDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportDetailsRepository extends CrudRepository<TransportDetails, Long> {}
