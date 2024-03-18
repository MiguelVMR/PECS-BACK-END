package com.pecs.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime criadoEm;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime alteradoEm;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletadoEm;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean disabled;

    public void setDisabled(Boolean disabled) {
        if (disabled) {
            this.alteradoEm = LocalDateTime.now();
            this.deletadoEm = LocalDateTime.now();
        } else {
            this.alteradoEm = LocalDateTime.now();
            this.deletadoEm = null;
        }

        this.disabled = disabled;
    }
}
