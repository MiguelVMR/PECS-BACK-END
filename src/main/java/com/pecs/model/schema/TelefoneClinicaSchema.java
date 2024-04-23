package com.pecs.model.schema;

import com.pecs.model.enums.TipoTelefone;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * The Class TelefoneClinicaSchema
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @sincer 01/04/2024
 */

@Entity
@Table(name = "telefone_clinica")
@Data
@NoArgsConstructor
public class TelefoneClinicaSchema {

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
    @JoinColumn(name = "clinica_id", foreignKey = @ForeignKey(name = "fk_clinica_x_telefone_clinica"))
    private ClinicaSchema clinica;
}