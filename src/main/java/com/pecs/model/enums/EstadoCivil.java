package com.pecs.model.enums;

public enum EstadoCivil {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    VIUVO("Viúvo"),
    SEPARADO_JUDICIALMENTE("Separado Judicialmente"),
    DIVORCIADO("Divorciado");

    private final String texto;

    EstadoCivil(String estadoCivil) {
        this.texto = estadoCivil;
    }

    public String getTexto() {
        return texto;
    }
}
