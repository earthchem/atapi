package org.earthchem.atapi.repository;

import java.util.Optional;

import org.earthchem.atapi.model.FundingSource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FundingSourceRepository extends CrudRepository<FundingSource, Integer> {
}