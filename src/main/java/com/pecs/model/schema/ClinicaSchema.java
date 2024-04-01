package com.pecs.model.schema;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.pecs.model.enums.TipoClinica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clinica")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClinicaSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "documento", length = 20, nullable = false, unique = true)
    private String documento;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(name = "email", columnDefinition = "VARCHAR(150) UNIQUE DEFAULT NULL")
    private String email;

    @Column(name = "tipo_clinica", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoClinica tipoClinica;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoClinicaSchema endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<TelefoneClinicaSchema> telefones;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id", foreignKey = @ForeignKey(name = "fk_clinica_x_consultas"))
    private List<ConsultaSchema> consultas;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinica_id", foreignKey = @ForeignKey(name = "fk_clinica_x_pacientes"))
    private List<PacienteSchema> pacientes;



}
