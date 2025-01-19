package com.caio.barbearia.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.caio.barbearia.entities.Agendamento;


@DataJpaTest
@Sql(scripts = "/scripts/data.sql") // Script SQL para popular o banco H2
class AgendamentoRepositoryTest {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Test
    void testFindByFuncionarioId() {
        Long funcionarioId = 1L;

        List<Agendamento> agendamentos = agendamentoRepository.findByFuncionarioId(funcionarioId);

        assertThat(agendamentos).isNotEmpty();
        assertThat(agendamentos.get(0).getFuncionarioProcedimento().getFuncionario().getId()).isEqualTo(funcionarioId);
    }

    @Test
    void testFindByClienteId() {
        Long clienteId = 1L;

        List<Agendamento> agendamentos = agendamentoRepository.findByClienteId(clienteId);

        assertThat(agendamentos).isNotEmpty();
        assertThat(agendamentos.get(0).getCliente().getId()).isEqualTo(clienteId);
    }

    @Test
    void testFindHorariosOcupadosByFuncionarioAndData() {
        Long funcionarioId = 1L;
        LocalDate data = LocalDate.of(2025, 1, 20);

        List<String> horariosOcupados = agendamentoRepository.findHorariosOcupadosByFuncionarioAndData(funcionarioId, data);

        assertThat(horariosOcupados).isNotEmpty();
        assertThat(horariosOcupados.get(0)).isEqualTo("10:00");
    }
}
