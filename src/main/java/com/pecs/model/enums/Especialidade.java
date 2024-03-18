package com.pecs.model.enums;

public enum Especialidade {
    CARDIOLOGIA("Cardiologia"),
    DERMATOLOGIA("Dermatologia"),
    GASTROENTEROLOGIA("Gastroenterologia"),
    GINECOLOGIA("Ginecologia"),
    NEUROLOGIA("Neurologia"),
    OFTALMOLOGIA("Oftalmologia"),
    ORTOPEDIA("Ortopedia"),
    PEDIATRIA("Pediatria"),
    PSIQUIATRIA("Psiquiatria"),
    UROLOGIA("Urologia");

    private final String nome;

    Especialidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
