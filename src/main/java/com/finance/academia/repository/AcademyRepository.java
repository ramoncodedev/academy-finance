package com.finance.academia.repository;

import com.finance.academia.model.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {

    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);
    public Academy findByName(String name);
}
