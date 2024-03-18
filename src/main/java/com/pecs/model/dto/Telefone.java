package com.pecs.model.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecs.model.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Telefone extends AbstractEntity {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String numero;

    private TipoTelefone tipo;

    private String ramal;

    private Clinica clinica;

    private Paciente paciente;

    private UsuarioClinico usuarioClinico;
}
