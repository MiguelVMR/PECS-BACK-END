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

    @Column(name = "criado_em")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime criadoEm;

    @Column(name = "alterado_em")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime alteradoEm;

    @Column(name = "deletado_em")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletadoEm;

    @Column(name = "desabilitado")
    private Boolean disabled;

}
