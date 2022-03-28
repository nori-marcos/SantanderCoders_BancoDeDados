package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  List<Usuario> findByNome(String nome);

  List<Usuario> findByNomeAAndCpf(String nome, String cpf);

  List<Usuario> findByNomeIs(String nome);

  List<Usuario> findByNomeIsNot(String nome);


}
