package com.caio.barbearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.FuncionarioProcedimento;

@Repository
public interface FuncionarioProcedimentoRepository extends JpaRepository<FuncionarioProcedimento, Long>{

    @Query("SELECT fp FROM FuncionarioProcedimento fp " +
           "JOIN FETCH fp.funcionario f " +
           "WHERE fp.procedimento.id = :procedimentoId")
    List<FuncionarioProcedimento> findFuncionariosByProcedimentoId(@Param("procedimentoId") Long procedimentoId);
}