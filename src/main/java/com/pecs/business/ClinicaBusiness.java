package com.pecs.business;

import com.pecs.gateway.ClinicaGateway;
import com.pecs.gateway.UsuarioClinicoGateway;
import com.pecs.model.dto.Clinica;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ClinicaBusiness(ClinicaGateway clinicaGateway,UsuarioClinicoGateway usuarioClinicoGateway) {
        this.clinicaGateway = clinicaGateway;
        this.usuarioClinicoGateway = usuarioClinicoGateway;
    }

    public Clinica save(Clinica clinica){

        return clinicaGateway.save(clinica);
    }
}
