package com.pecs.gateway;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.pecs.config.CustomModelConfig;
import com.pecs.config.keycloak.AdminKeycloakModelConfig;
import com.pecs.config.keycloak.UserKeycloakModelConfig;
import com.pecs.exception.PecsException;

@Component
public class KeycloakGateway {
    private final CustomModelConfig customModelConfig;

    @Autowired
    public KeycloakGateway(CustomModelConfig customModelConfig) {
        this.customModelConfig = customModelConfig;
    }

    public Keycloak getInstance() {
        AdminKeycloakModelConfig admin = customModelConfig.getKeycloak().getAdmin();

        return KeycloakBuilder.builder().realm(admin.getRealm()).serverUrl(customModelConfig.getKeycloak().getUrl())
                .clientId(admin.getClient())
                .password(admin.getPassword()).username(admin.getUsername()).clientSecret(admin.getSecret()).build();
    }

    public UsersResource getUsersResource() {
        Keycloak instance = getInstance();

        return instance.realm(customModelConfig.getKeycloak().getUser().getRealm()).users();
    }

    public AccessTokenResponse getAccessToken(String username, String password) {
        try {
            UserKeycloakModelConfig user = customModelConfig.getKeycloak().getUser();

            return KeycloakBuilder.builder().realm(user.getRealm()).serverUrl(customModelConfig.getKeycloak().getUrl())
                    .clientId(user.getClient()).username(username).password(password).clientSecret(user.getSecret())
                    .build().tokenManager().getAccessToken();
        } catch (Exception e) {
            throw new PecsException("Credenciais incorretas, tente novamente: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
