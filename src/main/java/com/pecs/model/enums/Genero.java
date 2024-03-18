package com.pecs.model.enums;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    OUTRO("Outro"),
    NAO_INFORMAR("Não Informar");

    private final String texto;

    private Genero(String genero) {
        this.texto = genero;
    }

    public String getTexto() {
        return texto;
    }
}
