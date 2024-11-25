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
           "ap.agendamento.id, ap.agendamento.dataHoraInicio, fp.procedimento.nome,  a.status.descricao) " +
           "FROM AgendamentoProcedimento ap " +
           "JOIN ap.funcionarioProcedimento fp " +
           "JOIN ap.agendamento a " +
           "WHERE fp.funcionario.id = :idFuncionario")
    List<AgendamentoProcedimentoDTO> findByFuncionarioId(@Param("idFuncionario") Long idFuncionario);

    
    @Query("SELECT new com.caio.barbearia.dto.AgendamentoProcedimentoDTO( " +
           "ap.agendamento.id, ap.agendamento.dataHoraInicio, fp.procedimento.nome, a.status.descricao) " +
           "FROM AgendamentoProcedimento ap " +
           "JOIN ap.agendamento a " +
           "JOIN ap.funcionarioProcedimento fp " +
           "WHERE a.cliente.id = :idCliente")
    List<AgendamentoProcedimentoDTO> findByClienteId(@Param("idCliente") Long idCliente);
     
}
