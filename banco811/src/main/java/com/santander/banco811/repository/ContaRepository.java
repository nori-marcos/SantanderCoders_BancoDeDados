package com.santander.banco811.repository;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

  List<Conta> findByValorLessThan(BigDecimal valor);
  List<Conta> findByValorLessThanEqual(BigDecimal valor);
  List<Conta> findByValorGreater(BigDecimal valor);
  List<Conta> findByValorGreaterThanEqual(BigDecimal valor);

  @Query("select c from Conta c " +
          "where (c.tipoConta = :tipoConta and c.usuario.cpf = :cpf) " +
          "or (c.tipoConta = :tipoConta or c.saldo = :saldo)")
  List<Conta> findByTipoContaAndCpfOrTipoContaAndSaldo(
          @Param("tipoConta") TipoConta tipoConta,
          @Param("cpf") String cpf,
          @Param("saldo") BigDecimal saldo
  );

  @Query(value = "select * from conta c" +
          "where (c.tipo_conta = :tipoConta AND" +
          "c.data_criacao >= :dataCriacao)" +
          "OR c.saldo = :saldo ", nativeQuery = true)
  List<Conta> findByDataCriacaoAndTipoContaOrSaldo(
          @Param("dataCriacao") LocalDateTime dataCriacao,
          @Param("tipoConta") LocalDateTime tipoConta,
          @Param("saldo") BigDecimal saldo
  );



}
