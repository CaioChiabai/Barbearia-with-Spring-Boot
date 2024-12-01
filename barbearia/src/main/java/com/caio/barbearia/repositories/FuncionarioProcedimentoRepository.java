package com.caio.barbearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse;

@Repository
public interface FuncionarioProcedimentoRepository extends JpaRepository<FuncionarioProcedimento, Long>{

    @Query("SELECT new com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse(fp.id, f.id, f.nome) " +
            "FROM FuncionarioProcedimento fp " +
            "JOIN Funcionario f ON fp.funcionario.id = f.id " +
            "WHERE fp.procedimento.id = :procedimentoId")
    List<FuncionarioProcedimentoResponse> findFuncionariosByProcedimentoId(@Param("procedimentoId") Long procedimentoId);
}