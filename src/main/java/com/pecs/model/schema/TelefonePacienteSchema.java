package com.pecs.model.schema;

import java.util.UUID;

import com.pecs.model.enums.TipoTelefone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import jakarta.persistence.Temporal;
// import jakarta.persistence.TemporalType;
// import jakarta.persistence.UniqueConstraint;
import lombok.Data;
// import lombok.EqualsAndHashCode;
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

    // @ManyToOne
    // @JoinColumn(name = "paciente_id") // nome da coluna na tabela do banco de
    // dados que referencia o paciente
    // private PacienteSchema paciente; // atributo que representa o paciente
    // associado a este telefone
    @ManyToOne
    @JoinColumn(name = "paciente_id", foreignKey = @ForeignKey(name = "fk_paciente_x_telefone_paciente"))
    private PacienteSchema paciente;
}
