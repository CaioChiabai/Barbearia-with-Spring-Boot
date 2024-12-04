package com.caio.barbearia.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

    @Query("SELECT a FROM Agendamento a " +
           "JOIN FETCH a.cliente c " +
           "JOIN FETCH a.funcionarioProcedimento fp " +
           "WHERE fp.funcionario.id = :idFuncionario")
    List<Agendamento> findByFuncionarioId(@Param("idFuncionario") Long idFuncionario);

    @Query("SELECT a FROM Agendamento a " +
           "JOIN FETCH a.cliente c " +
           "JOIN FETCH a.funcionarioProcedimento fp " +
           "WHERE a.cliente.id = :idCliente")
    List<Agendamento> findByClienteId(@Param("idCliente") Long idCliente);

    @Query("SELECT a.horaInicio FROM Agendamento a " +
           "WHERE a.funcionarioProcedimento.funcionario.id = :idFuncionario " +
           "AND a.data = :data")
    List<String> findHorariosOcupadosByFuncionarioAndData(
            @Param("idFuncionario") Long idFuncionario, 
            @Param("data") LocalDate data);
}
