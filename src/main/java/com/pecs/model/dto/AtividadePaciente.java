package com.pecs.model.dto;

import java.util.Date;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class AtividadePaciente extends AbstractEntity {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private Date dataAtividade;

    private String observacoes;

    private Paciente paciente;

    private Clinica clinica;

}
