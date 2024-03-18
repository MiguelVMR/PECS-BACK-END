package com.pecs.model.schema;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consulta", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ConsultaSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_consulta", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_consulta;

    @Column(length = 5000, nullable = false)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteSchema paciente;

    @ManyToOne
    @JoinColumn(name = "clinica_id", nullable = false)
    private ClinicaSchema clinica;

    @ManyToOne
    @JoinColumn(name = "usuario_clinico_id", nullable = false)
    private UsuarioClinicoSchema usuarioClinico;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private List<DiagnosticoSchema> diagnosticos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulta", fetch = FetchType.LAZY)
    private List<PrescricaoSchema> prescricoes;

}
