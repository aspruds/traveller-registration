package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.traveller.ContactInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends CrudRepository<ContactInformation, Long> {}
