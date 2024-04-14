package com.pecs.model.repository;

import com.pecs.model.schema.ConsultaSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaSchema, UUID> {
    List<ConsultaSchema> findAllByUsuarioClinico_Id(UUID usuarioClinico);

    List<ConsultaSchema> findAllByPaciente_Id(UUID pacienteId);
}
