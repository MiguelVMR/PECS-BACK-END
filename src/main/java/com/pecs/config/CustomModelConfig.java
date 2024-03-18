package com.pecs.config;

import com.pecs.config.keycloak.KeycloakModelConfig;

import lombok.Data;

@Data
public class CustomModelConfig {
    private KeycloakModelConfig keycloak;
}
