package com.pecs.model.dto;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pecs.model.enums.TipoClinica;
import com.pecs.model.schema.UsuarioClinicoSchema;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Consulta> consultas;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Paciente> pacientes;

}
