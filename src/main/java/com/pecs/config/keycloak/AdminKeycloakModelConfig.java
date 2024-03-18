package com.pecs.config.keycloak;

import lombok.Data;

@Data
public class AdminKeycloakModelConfig {
    private String client;

    private String secret;

    private String username;

    private String password;

    private String realm;
}
