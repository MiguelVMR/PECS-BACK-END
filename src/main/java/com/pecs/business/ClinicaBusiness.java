package com.pecs.business;

import com.pecs.gateway.ClinicaGateway;
import com.pecs.gateway.UsuarioClinicoGateway;
import com.pecs.model.dto.Clinica;
import com.pecs.model.dto.UsuarioClinico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * The Class ClinicaBusiness
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 20/03/2024
 */

@Component
public class ClinicaBusiness {

    private final ClinicaGateway clinicaGateway;
    private final UsuarioClinicoGateway usuarioClinicoGateway;

    @Autowired
    public ClinicaBusiness(ClinicaGateway clinicaGateway, UsuarioClinicoGateway usuarioClinicoGateway) {
        this.clinicaGateway = clinicaGateway;
        this.usuarioClinicoGateway = usuarioClinicoGateway;
    }

    public Clinica save(Clinica clinica, JwtAuthenticationToken token) {

        String keycloakId = (String) token.getTokenAttributes().get("sub");

        UsuarioClinico usuarioDb = usuarioClinicoGateway.findByKeycloakUserId(keycloakId);

        clinica = clinicaGateway.save(clinica);

        usuarioDb.setClinica(clinica);

        usuarioClinicoGateway.save(usuarioDb);

        return clinica;
    }

    public Clinica findByUsuario(JwtAuthenticationToken token) {
        String keycloakId = (String) token.getTokenAttributes().get("sub");

        UsuarioClinico usuarioDb = usuarioClinicoGateway.findByKeycloakUserId(keycloakId);

        return usuarioDb.getClinica();

    }
}
