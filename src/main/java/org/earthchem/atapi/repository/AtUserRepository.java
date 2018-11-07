package org.earthchem.atapi.repository;

import java.util.Optional;

import org.earthchem.atapi.model.AtUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AtUserRepository extends CrudRepository<AtUser, Long> {
}
