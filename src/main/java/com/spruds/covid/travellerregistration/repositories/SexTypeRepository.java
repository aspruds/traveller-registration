package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.traveller.SexType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SexTypeRepository extends CrudRepository<SexType, Long>,
        AbstractTypeRepository<SexType> {}
