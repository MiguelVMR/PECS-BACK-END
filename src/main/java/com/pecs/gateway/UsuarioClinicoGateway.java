package com.pecs.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.pecs.exception.PecsException;
import com.pecs.model.dto.UsuarioClinico;
import com.pecs.model.repository.UsuarioClinicoRepository;
import com.pecs.model.schema.UsuarioClinicoSchema;
import com.pecs.utils.Mapper;

import jakarta.validation.constraints.NotBlank;

@Component
public class UsuarioClinicoGateway {

    private final Mapper mapper = new Mapper();

    private final UsuarioClinicoRepository usuarioRepository;

    public UsuarioClinicoGateway(UsuarioClinicoRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public void save(UsuarioClinico usuario) {
        usuarioRepository.save(mapper.converter(usuario, UsuarioClinicoSchema.class));
    }


    public UsuarioClinico findByEmail(String email) {
        UsuarioClinicoSchema usuarioSchema = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new PecsException("Usuario não encontrado", HttpStatus.NOT_FOUND));

        if (usuarioSchema.getDisabled() == true) {
            throw new PecsException("Usuario está desabilitado", HttpStatus.UNAUTHORIZED);
        }

        return mapper.converter(usuarioSchema, UsuarioClinico.class);
    }

    public UsuarioClinico findByKeycloakUserId(String keycloakId){
        return mapper.converter(usuarioRepository.findByKeycloakUserId(keycloakId), UsuarioClinico.class);
    }

}
