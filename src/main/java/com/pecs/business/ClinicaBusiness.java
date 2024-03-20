package com.pecs.business;

import com.pecs.exception.PecsException;
import com.pecs.gateway.ClinicaGateway;
import com.pecs.gateway.UsuarioClinicoGateway;
import com.pecs.model.dto.Clinica;
import com.pecs.model.dto.UsuarioClinico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    public ClinicaBusiness(ClinicaGateway clinicaGateway,UsuarioClinicoGateway usuarioClinicoGateway) {
        this.clinicaGateway = clinicaGateway;
        this.usuarioClinicoGateway = usuarioClinicoGateway;
    }

    public Clinica save(Clinica clinica){

        return clinicaGateway.save(clinica);
    }
}
