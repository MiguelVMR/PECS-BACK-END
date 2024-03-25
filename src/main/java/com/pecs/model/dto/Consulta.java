package com.pecs.model.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Consulta extends AbstractEntity {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private Date dataConsulta;

    private String observacoes;

    private List<Diagnostico> diagnosticos;

    private List<Prescricao> prescricoes;
}
