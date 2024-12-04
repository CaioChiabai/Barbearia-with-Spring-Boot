package com.caio.barbearia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.JornadaTrabalho;

@Repository
public interface JornadaTrabalhoRepository extends JpaRepository<JornadaTrabalho, Long>{

    Optional<JornadaTrabalho> findByFuncionarioId(Long idFuncionario);
}
