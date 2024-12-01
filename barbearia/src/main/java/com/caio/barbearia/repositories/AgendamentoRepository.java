package com.caio.barbearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.dto.AgendamentoDTO;
import com.caio.barbearia.entities.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

    @Query("SELECT new com.caio.barbearia.dto.AgendamentoDTO( " +
        "a.id, a.data, a.horaInicio, c, fp, a.status) " +
        "FROM Agendamento a " +
        "JOIN a.cliente c " +
        "JOIN a.funcionarioProcedimento fp " +
        "WHERE fp.funcionario.id = :idFuncionario")
    List<AgendamentoDTO> findByFuncionarioId(@Param("idFuncionario") Long idFuncionario);

    @Query("SELECT new com.caio.barbearia.dto.AgendamentoDTO( " +
            "a.id, a.data, a.horaInicio, c, fp, a.status) " +
            "FROM Agendamento a " +
            "JOIN a.cliente c " +
            "JOIN a.funcionarioProcedimento fp " +
            "WHERE a.cliente.id = :idCliente")
    List<AgendamentoDTO> findByClienteId(@Param("idCliente") Long idCliente);
}
