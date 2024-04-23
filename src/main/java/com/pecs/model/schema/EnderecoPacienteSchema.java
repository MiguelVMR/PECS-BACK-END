package com.pecs.model.schema;

import jakarta.persistence.*;
import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

/**
 * The Class EnderecoPacienteSchema
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 01/04/2024
 */

@Entity
@Table(name = "endereco_paciente")
@Data
@NoArgsConstructor
public class EnderecoPacienteSchema {

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
}
