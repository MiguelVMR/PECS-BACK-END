package com.pecs.model.schema;

import java.util.UUID;

import com.pecs.model.enums.TipoTelefone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefone_paciente")
@Data
@NoArgsConstructor
public class TelefonePacienteSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 15, nullable = false)
    private String numero;

    @Column(name = "tipo", length = 20, nullable = false)
    private TipoTelefone tipo;

    @Column(name = "ramal", columnDefinition = "VARCHAR(15) DEFAULT NULL")
    private String ramal;


}
