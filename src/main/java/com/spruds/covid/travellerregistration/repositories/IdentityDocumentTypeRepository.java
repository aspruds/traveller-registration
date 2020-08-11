package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.id.IdentityDocumentType;
import com.spruds.covid.travellerregistration.model.db.traveller.SexType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityDocumentTypeRepository extends CrudRepository<IdentityDocumentType, Long>,
        AbstractTypeRepository<IdentityDocumentType> {}
