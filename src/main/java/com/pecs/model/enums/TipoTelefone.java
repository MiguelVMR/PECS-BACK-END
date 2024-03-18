package com.pecs.model.enums;

public enum TipoTelefone {
    CELULAR("Celular"),
    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private final String texto;

    TipoTelefone(String tipoTelefone) {
        this.texto = tipoTelefone;
    }

    public String getTexto() {
        return texto;
    }
}
