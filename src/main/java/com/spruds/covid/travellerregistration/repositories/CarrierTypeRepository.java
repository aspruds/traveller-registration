package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.transport.CarrierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrierTypeRepository extends CrudRepository<CarrierType, Long> {
    Optional<CarrierType> findByCode(String code);
}
