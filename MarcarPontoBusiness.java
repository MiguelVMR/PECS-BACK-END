package com.ponto.ponto_digital.business;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import com.ponto.ponto_digital.exception.PontoException;
import com.ponto.ponto_digital.gateway.MarcarPontoGateway;
import com.ponto.ponto_digital.gateway.UsuarioGateway;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.model.dto.Usuario;

@Component
public class MarcarPontoBusiness {
    private final UsuarioGateway usuarioGateway;

    private final MarcarPontoGateway marcarPontoGateway;

    @Autowired
    public MarcarPontoBusiness(UsuarioGateway usuarioGateway, MarcarPontoGateway marcarPontoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.marcarPontoGateway = marcarPontoGateway;
    }

    public MarcarPonto baterPonto(MarcarPonto marcarPonto, JwtAuthenticationToken token){

        String keycloakId =(String) token.getTokenAttributes().get("sub");

        Usuario usuarioDb = usuarioGateway.findById(keycloakId);

        if (Objects.isNull(usuarioDb)) {
            throw new PontoException("Não existe usuario com este id",
                    HttpStatus.BAD_REQUEST);
        }

        marcarPonto.setUsuario(usuarioDb);
        return marcarPontoGateway.baterPonto(marcarPonto);
    }



}
