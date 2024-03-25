package com.pecs.model.dto;

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
public class Prescricao extends AbstractEntity{
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String medicamento;

    private String dosagem;

}
