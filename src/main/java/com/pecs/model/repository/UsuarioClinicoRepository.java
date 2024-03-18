package com.pecs.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecs.model.schema.UsuarioClinicoSchema;

@Repository
public interface UsuarioClinicoRepository extends JpaRepository<UsuarioClinicoSchema,UUID>{

    Optional<UsuarioClinicoSchema> findByEmail(String email);

    UsuarioClinicoSchema findByKeycloakUserId(String keycloakId);
    
}
