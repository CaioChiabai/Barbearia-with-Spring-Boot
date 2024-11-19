package com.caio.barbearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.FuncionarioProcedimento;

@Repository
public interface FuncionarioProcedimentoRepository extends JpaRepository<FuncionarioProcedimento, Long>{

}
