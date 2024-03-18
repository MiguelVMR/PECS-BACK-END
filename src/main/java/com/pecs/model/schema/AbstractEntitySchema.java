package com.pecs.model.schema;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbstractEntitySchema {

    @Column(name = "criado_em", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime criadoEm;

    @Column(name = "alterado_em", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime alteradoEm;

    @Column(name = "deletado_em", nullable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletadoEm;

    @Column(name = "desabilitado")
    private Boolean disabled;

}
