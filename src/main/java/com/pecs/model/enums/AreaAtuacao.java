package com.pecs.model.enums;

public enum AreaAtuacao {
    CLINICA("Clínica"),
    EDUCACIONAL("Educacional"),
    ORGANIZACIONAL("Organizacional"),
    SOCIAL("Social"),
    ESPORTIVA("Esportiva"),
    SAUDE_PUBLICA("Saúde Pública"),
    FORENSE("Forense");

    private final String nome;

    AreaAtuacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
