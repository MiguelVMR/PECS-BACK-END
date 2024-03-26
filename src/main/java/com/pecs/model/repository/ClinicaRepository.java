package com.pecs.model.repository;

import com.pecs.model.schema.ClinicaSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The Interface ClinicaRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 20/03/2024
 */
@Repository
public interface ClinicaRepository extends JpaRepository<ClinicaSchema, UUID> {

}
