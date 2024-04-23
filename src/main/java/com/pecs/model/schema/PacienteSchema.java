package com.pecs.model.schema;

import java.util.Date;
// import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

import com.pecs.model.enums.EstadoCivil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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
    @JoinColumn(name = "endereco_paciente_id", nullable = false)
    private EnderecoPacienteSchema endereco;

    @ManyToOne
    @JoinColumn(name = "clinica_id", foreignKey = @ForeignKey(name = "fk_clinica_x_paciente"))
    private ClinicaSchema clinica;

}
