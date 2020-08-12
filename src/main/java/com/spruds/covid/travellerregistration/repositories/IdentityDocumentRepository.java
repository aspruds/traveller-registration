package com.spruds.covid.travellerregistration.repositories;

import com.spruds.covid.travellerregistration.model.db.id.IdentityDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentRepository extends CrudRepository<IdentityDocument, Long> {}
