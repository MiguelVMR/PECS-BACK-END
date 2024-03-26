package com.pecs.model.dto;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecs.model.enums.AreaAtuacao;
import com.pecs.model.enums.Especialidade;
import com.pecs.model.schema.PacienteSchema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioClinico extends AbstractEntity{

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "O nome da Pessoa é obrigatório")
    private String nome;

    private String email;

    private String cpf;

    @JsonIgnore
    private String senha = String.format("%08d", new Random().nextInt(99999999)); 

    private String crm;

    private String crp;

    private AreaAtuacao areaAtuacao;

    private Especialidade especialidade;

    private Endereco endereco;

    private List<Telefone> telefones;

    @JsonIgnore
    private String tokenSenha;

    @JsonIgnore
    private boolean firstLogin;

    @JsonIgnore
    private String keycloakUserId;

    private List<Consulta> consultas;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean emailConfirmado = false;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Paciente> pacientes;

    private Clinica clinica;

}
