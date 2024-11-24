package com.caio.barbearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.dto.AgendamentoProcedimentoDTO;
import com.caio.barbearia.entities.AgendamentoProcedimento;

@Repository
public interface AgendamentoProcedimentoRepository extends JpaRepository<AgendamentoProcedimento, Long>{

    @Query("SELECT new com.caio.barbearia.dto.AgendamentoProcedimentoDTO( " +
           "ap.agendamento.id, ap.agendamento.dataHoraInicio, fp.procedimento.nome) " +
           "FROM AgendamentoProcedimento ap " +
           "JOIN ap.funcionarioProcedimento fp " +
           "WHERE fp.funcionario.id = :idFuncionario")
    List<AgendamentoProcedimentoDTO> findAllByFuncionarioId(@Param("idFuncionario") Long idFuncionario);
}
