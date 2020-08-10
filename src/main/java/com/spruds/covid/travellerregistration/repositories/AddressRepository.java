package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.address.Address;
import com.spruds.covid.travellerregistration.model.db.address.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {}
