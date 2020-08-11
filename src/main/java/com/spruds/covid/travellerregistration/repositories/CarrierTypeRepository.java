package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.transport.CarrierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierTypeRepository extends CrudRepository<CarrierType, Long>,
        AbstractTypeRepository<CarrierType> {}