package com.santander.banco811.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "conta")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "agencia")
  private Integer agencia;

  @Column(name = "data_criacao")
  @CreatedDate
  private LocalDateTime dataCriacao;

  @Column(name = "data_atualizacao")
  @LastModifiedDate
  private LocalDateTime dataAtualizacao;

  @Column(name = "saldo")
  private BigDecimal saldo;

  @Column(name = "tipo_conta")
  //  @Enumerated(EnumType.ORDINAL) com esta notacao ficaria pf como 1 e pj como 0, binario
  @Enumerated(EnumType.STRING)
  private TipoConta tipoConta;

//  todas as alteracoes feitas aqui serao feitas em cascata para o usuario
  @ManyToOne(cascade = CascadeType.ALL)
//  aqui estamos pedindo para entrar na classe usuario e pegar o id
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

}
