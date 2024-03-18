package com.pecs.model.schema;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EnderecoSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 255, nullable = false)
    private String logradouro;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 5, nullable = false)
    private String uf;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL DEFAULT 'Brasil'")
    private String pais;

    @Column(length = 15, nullable = false)
    private String cep;

    @Column(length = 100, nullable = false)
    private String complemento;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private ClinicaSchema clinica;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private PacienteSchema paciente;

    @JsonIgnore
    @OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
    private UsuarioClinicoSchema usuarioClinico;

}
