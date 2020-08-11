package com.spruds.covid.travellerregistration.repositories;

import java.util.Optional;

public interface AbstractTypeRepository<T> {
    public Optional<T> findByCode(String code);
}
