package com.pecs.model.schema;

// import java.util.Date;
// import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diagnostico")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DiagnosticoSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 5000, nullable = false)
    private String descricoes;

    @ManyToOne
    @JoinColumn(name = "consulta_id", foreignKey = @ForeignKey(name = "fk_consulta_x_diagnostico"))
    private ConsultaSchema consulta;

}
