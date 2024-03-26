package com.pecs.business;

import com.pecs.gateway.UsuarioClinicoGateway;
import com.pecs.model.dto.Paciente;
import com.pecs.model.dto.UsuarioClinico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Class PacienteBusiness
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 26/03/2024
 */
@Component
public class PacienteBusiness {

    private final UsuarioClinicoGateway usuarioClinicoGateway;

    @Autowired
    public PacienteBusiness(UsuarioClinicoGateway usuarioClinicoGateway) {
        this.usuarioClinicoGateway = usuarioClinicoGateway;
    }

    public Paciente salvarPacienteVinculadoUsuario(Paciente paciente, JwtAuthenticationToken token) {

        String keycloakId = (String) token.getTokenAttributes().get("sub");

        UsuarioClinico usuarioDb = usuarioClinicoGateway.findByKeycloakUserId(keycloakId);

        usuarioDb.getPacientes().add(paciente);

        usuarioClinicoGateway.save(usuarioDb);

        return paciente;
    }

    public List<Paciente> findAllPacientesUser(JwtAuthenticationToken token){

        String keycloakId = (String) token.getTokenAttributes().get("sub");

        UsuarioClinico usuarioDb = usuarioClinicoGateway.findByKeycloakUserId(keycloakId);

        return usuarioDb.getPacientes();
    }
}
