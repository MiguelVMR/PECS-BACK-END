package com.pecs.model.enums;

public enum TipoAtividade {
    CONSULTA("Consulta"),
    EXAME("Exame"),
    MEDICACAO("Medicação"),
    TERAPIA("Terapia"),
    OUTRO("Outro");

    private final String nome;

    TipoAtividade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
