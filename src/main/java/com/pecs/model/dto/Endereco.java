package com.pecs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends AbstractEntity{

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String logradouro;

    private String bairro;

    private String cidade;

    private String uf;

    private String pais;

    private String cep;

    private String complemento;

    private Clinica clinica;

    private Paciente paciente;

    private UsuarioClinico usuarioClinico;
}
