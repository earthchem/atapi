package org.earthchem.atapi.repository;

import org.earthchem.atapi.model.SubmitStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitStatusRepository extends CrudRepository<SubmitStatus, Integer> {
}