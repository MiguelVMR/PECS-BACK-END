package com.pecs.config.keycloak;

import lombok.Data;

@Data
public class UserKeycloakModelConfig {

    private String client;

    private String secret;

    private String realm;
}
