package com.caio.barbearia.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import com.caio.barbearia.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByUserId(String userId);
}
