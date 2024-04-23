package com.pecs.business;

import com.pecs.model.dto.Consulta;
import com.pecs.gateway.ConsultaGateway;
import com.pecs.model.dto.UsuarioClinico;
import com.pecs.gateway.UsuarioClinicoGateway;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class ConsultaBusiness {

    private final ConsultaGateway consultaGateway;
    private final UsuarioClinicoGateway usuarioClinicoGateway;

    @Autowired
    public ConsultaBusiness(ConsultaGateway consultaGateway, UsuarioClinicoGateway usuarioClinicoGateway) {
        this.consultaGateway = consultaGateway;
        this.usuarioClinicoGateway = usuarioClinicoGateway;
    }

    public Consulta save(Consulta consulta, JwtAuthenticationToken token) {
        return consultaGateway.save(consulta);
    }

    public List<Consulta> findByUser(JwtAuthenticationToken token) {
        String keycloakId = (String) token.getTokenAttributes().get("sub");

        UsuarioClinico usuarioDb = usuarioClinicoGateway.findByKeycloakUserId(keycloakId);

        return consultaGateway.findAllByUser_Id(usuarioDb.getId());
    }

    public List<Consulta> findByPacienteId(UUID patientUUID) {

        return consultaGateway.findAllByPacienteId(patientUUID);
    }

}
