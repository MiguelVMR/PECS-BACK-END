package com.pecs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pecs.model.dto.UsuarioClinico;

@Service
public class JavaMailService {

    private final JavaMailBuilder javaMailBuilder;

    @Autowired
    public JavaMailService(JavaMailBuilder javaMailBuilder) {
        this.javaMailBuilder = javaMailBuilder;
    }

    public void senderEmailCreate(UsuarioClinico usuario) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", usuario.getNome());
        params.put("senha", usuario.getSenha());

        javaMailBuilder.to(usuario.getEmail())
                .subject("Senha de cadastro")
                .fire("autenticacao/create_user/body.ftl", params);
    }

    public void senderEmailToken(UsuarioClinico usuario, Integer token) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", usuario.getNome());
        params.put("token", token.toString());

        javaMailBuilder.to(usuario.getEmail())
                .subject("Token para redefinir a senha")
                .fire("autenticacao/redefinir_senha/body.ftl", params);
    }
}
