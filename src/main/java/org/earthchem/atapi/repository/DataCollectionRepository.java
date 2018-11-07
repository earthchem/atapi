package org.earthchem.atapi.repository;

import org.earthchem.atapi.model.DataCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCollectionRepository extends CrudRepository<DataCollection, Long> {
}