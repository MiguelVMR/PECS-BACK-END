package com.pecs.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public class AbstractEntity {

    @Column(name = "criado_em", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime criadoEm;

    @Column(name = "alterado_em", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime alteradoEm;

    @Column(name = "deletado_em", nullable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletadoEm;

    @PrePersist
    public void prePersistTimestamp() {
        criadoEm = LocalDateTime.now();
        alteradoEm = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdateTimestamp() {
        alteradoEm = LocalDateTime.now();
    }

}
