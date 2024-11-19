package com.caio.barbearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.AgendamentoProcedimento;

@Repository
public interface AgendamentoProcedimentoRepository extends JpaRepository<AgendamentoProcedimento, Long>{

}
