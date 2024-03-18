package com.pecs.config.keycloak;

import lombok.Data;

@Data
public class KeycloakModelConfig {

    private String url;

    private AdminKeycloakModelConfig admin;

    private UserKeycloakModelConfig user;
}
