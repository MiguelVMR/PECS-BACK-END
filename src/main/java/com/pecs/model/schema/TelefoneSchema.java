package com.pecs.model.schema;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pecs.model.enums.TipoTelefone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefones", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TelefoneSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 15, nullable = false)
    private String numero;

    @Column(name = "tipo", length = 20, nullable = false)
    private TipoTelefone tipo;

    @Column(name = "ramal", columnDefinition = "VARCHAR(15) DEFAULT NULL")
    private String ramal;

    @ManyToOne
    @JoinColumn(name = "clinica_id", nullable = false)
    private ClinicaSchema clinica;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteSchema paciente;

    @ManyToOne
    @JoinColumn(name = "usuarioClinico_id", nullable = false)
    private UsuarioClinicoSchema usuarioClinico;

}
