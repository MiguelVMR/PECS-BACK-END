package com.pecs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecs.model.enums.EstadoCivil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Paciente extends AbstractEntity{
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String documento;

    private String nome;

    private String email;

    private String nomeMae;

    private String nomePai;

    private EstadoCivil estadoCivil;

    private Date dataNascimento;

    private String rg;

    private String orgaoEmissor;

    private Boolean politicamenteExposto;

    private Endereco endereco;

    private List<Telefone> telefones;

    private List<Consulta> consultas;

    private List<AtividadePaciente> atividadePacientes;
}
