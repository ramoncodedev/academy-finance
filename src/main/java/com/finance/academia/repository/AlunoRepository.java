package com.finance.academia.repository;

import com.finance.academia.model.usuario.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    AlunoModel findByNomeContainingIgnoreCase(String nome);
    Optional<AlunoModel> findByCpf(String cpf);
}
