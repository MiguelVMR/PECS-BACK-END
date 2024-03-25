package com.pecs.model.schema;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pecs.model.enums.EstadoCivil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PacienteSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "documento", length = 20, nullable = false, unique = true)
    private String documento;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "email", columnDefinition = "VARCHAR(150) UNIQUE DEFAULT NULL")
    private String email;

    @Column(name = "nome_mae", length = 150, nullable = false)
    private String nomeMae;

    @Column(name = "nome_pai", columnDefinition = "VARCHAR(150) DEFAULT NULL")
    private String nomePai;

    @Column(name = "estado_civil", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(name = "data_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(length = 20, nullable = false)
    private String rg;

    @Column(name = "orgao_emissor", length = 20, nullable = false)
    private String orgaoEmissor;

    @Column(name = "politicamente_exposto", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private Boolean politicamenteExposto;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoSchema endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<TelefoneSchema> telefones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", foreignKey = @ForeignKey(name = "fk_paciente_x_consulta"))
    private List<ConsultaSchema> consultas;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<AtividadePacienteSchema> atividadePacientes;


}
