package com.pecs.model.dto;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecs.model.enums.TipoClinica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Clinica extends AbstractEntity {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String documento;

    private String nome;

    private String email;

    private TipoClinica tipoClinica;

    private Endereco endereco;

    private List<Telefone> telefones;

    private List<Consulta> consultas;

    private List<Diagnostico> diagnosticos;

    private List<Prescricao> prescricoes;

    private List<AtividadePaciente> atividadePacientes;


}
