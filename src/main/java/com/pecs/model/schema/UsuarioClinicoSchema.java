package com.pecs.model.schema;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.pecs.model.enums.AreaAtuacao;
import com.pecs.model.enums.Especialidade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_clinico")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UsuarioClinicoSchema extends AbstractEntitySchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 11)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column(name = "crm")
    private String crm;

    @Column(name = "crp")
    private String crp;

    @Column(name = "area_atuacao")
    @Enumerated(EnumType.STRING)
    private AreaAtuacao areaAtuacao;

    @Column(name = "especialidade")
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoSchema endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioClinico", fetch = FetchType.LAZY)
    private List<TelefoneSchema> telefones;

    @Column(name = "token_senha", length = 8)
    private String tokenSenha;

    @Column(name = "firs_login")
    private boolean firstLogin;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;
    
    @Column(name = "email_confirmado")
    private Boolean emailConfirmado = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioClinico", fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_usuario_x_consulta"))
    private List<ConsultaSchema> consultas;

}
