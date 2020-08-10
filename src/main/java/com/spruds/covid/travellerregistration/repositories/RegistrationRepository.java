package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long> {}
