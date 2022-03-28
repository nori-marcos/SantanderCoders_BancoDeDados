package com.santander.banco811.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.santander.banco811.dto.UsuarioRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// procedendo com a criação da tabela forte
@Table(name = "usuario")
@Entity
@Getter
@Setter
// O JPA precisa de um construtor vazio para funcionar
@NoArgsConstructor
// Aqui estamos dizendo que esta classe está sendo auditada, observada pela AuditingEntityListener
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
  // Aqui estamos dizendo que o id vai ser autoincrementado
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  //    nao pode ser falso, nulo
  @Column(name = "cpf", nullable = false, unique = true)
  private String cpf;

  @Column(name = "senha")
  private String senha;

  @Column(name = "nome")
  private String nome;

  //    o JPA consegue gerenciar automaticamente a data de criacao e a data de atualizacao
  //    por meio de CreateDate e por meio de LastModifiedDate
  //    @EntityListeners(AuditingEntityListener.class)  é necessário para gerenciar os outros
  // anotadores
  @Column(name = "data_criacao")
  @CreatedDate
  private LocalDateTime dataCriacao;

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  //  notacao para dizer que os dados podem ser apenas lidos e nao escritos
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Conta> contas;

  public Usuario(UsuarioRequest usuarioRequest) {
    this.cpf = usuarioRequest.getCpf();
    this.nome = usuarioRequest.getNome();
    this.senha = usuarioRequest.getSenha();
  }



}
