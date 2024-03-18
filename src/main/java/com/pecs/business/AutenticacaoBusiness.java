package com.pecs.business;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.ObjectUtils;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;

import com.pecs.exception.PecsException;
import com.pecs.gateway.KeycloakGateway;
import com.pecs.gateway.UsuarioClinicoGateway;
import com.pecs.model.dto.UsuarioClinico;
import com.pecs.model.record.LoginRecord;
import com.pecs.model.record.ResetSenhaFirstLogin;
import com.pecs.model.record.ResetarSenhaExterna;
import com.pecs.service.JavaMailService;
import com.pecs.utils.Mapper;
import com.pecs.utils.Validators;
import jakarta.ws.rs.core.Response;

@Component
public class AutenticacaoBusiness {

    private final Mapper mapper = new Mapper();
    private final Validators validators = new Validators();
    private final UsuarioClinicoGateway usuarioGateway;
    private final KeycloakGateway keycloakGateway;
    private final JavaMailService javaMailService;

    @Autowired
    public AutenticacaoBusiness(UsuarioClinicoGateway usuarioGateway, KeycloakGateway keycloakGateway,
            JavaMailService javaMailService) {
        this.usuarioGateway = usuarioGateway;
        this.keycloakGateway = keycloakGateway;
        this.javaMailService = javaMailService;
    }

    public void criarUsuario(UsuarioClinico usuario) {

        validators.validacaoCpf(usuario.getCpf());

        UserRepresentation userRepresentation = mapper.converter(usuario, true);

        Response response = keycloakGateway.getUsersResource().create(userRepresentation);

        if (response.getStatus() != 201) {
            throw new PecsException("Erro inesperado no Keycloak", HttpStatus.BAD_REQUEST);
        }

        String location = response.getMetadata().get("Location").toString().replace("]", "");
        Integer index = location.lastIndexOf("users/");

        usuario.setKeycloakUserId(location.substring(index + "users/".length()));

        usuarioGateway.save(usuario);

        javaMailService.senderEmailCreate(usuario);
    }

    public Map<String, Object> login(LoginRecord loginRecord) {

        UsuarioClinico usuarioDB = usuarioGateway.findByEmail(loginRecord.email());

        AccessTokenResponse accessToken = keycloakGateway.getAccessToken(loginRecord.email(), loginRecord.password());

        Map<String, Object> response = new HashMap<>();

        response.put("first_login", usuarioDB.isFirstLogin());
        response.put("email", usuarioDB.getEmail());
        response.put("accessToken", accessToken.getToken());

        return response;
    }

    public void geraTokenResetSenha(String email) {
        UsuarioClinico usuario = usuarioGateway.findByEmail(email);
        Integer tokenSenha = new Random().nextInt(999999);

        usuario.setTokenSenha(tokenSenha.toString());

        usuarioGateway.save(usuario);

        javaMailService.senderEmailToken(usuario, tokenSenha);

    }

    public AccessTokenResponse recuperarSenhaDeslogado(ResetarSenhaExterna resetarSenhaExterna) {

        UsuarioClinico usuario = usuarioGateway.findByEmail(resetarSenhaExterna.email());

        if (ObjectUtils.notEqual(usuario.getTokenSenha(), resetarSenhaExterna.token())) {
            throw new PecsException("O token de recuperação está incorreto", HttpStatus.BAD_REQUEST);
        }

        UserResource userResource = keycloakGateway.getUsersResource().get(usuario.getKeycloakUserId());

        CredentialRepresentation credentialRepresentation = mapper
                .credentialRepresentation(resetarSenhaExterna.senha());

        userResource.resetPassword(credentialRepresentation);

        AccessTokenResponse accessTokenResponse = keycloakGateway.getAccessToken(resetarSenhaExterna.email(),
                resetarSenhaExterna.senha());

        usuario.setSenha(resetarSenhaExterna.senha());

        usuarioGateway.save(usuario);

        return accessTokenResponse;
    }

    public AccessTokenResponse recuperarSenhaFirstLogin(ResetSenhaFirstLogin firstLogin) {

        UsuarioClinico usuario = usuarioGateway.findByEmail(firstLogin.email());

        UserResource userResource = keycloakGateway.getUsersResource().get(usuario.getKeycloakUserId());

        CredentialRepresentation credentialRepresentation = mapper.credentialRepresentation(firstLogin.senha());

        userResource.resetPassword(credentialRepresentation);

        AccessTokenResponse accessTokenResponse = keycloakGateway.getAccessToken(firstLogin.email(),
                firstLogin.senha());

        usuario.setSenha(firstLogin.senha());

        usuarioGateway.save(usuario);

        return accessTokenResponse;
    }

}
