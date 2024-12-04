package com.caio.barbearia.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.entities.JornadaTrabalho;
import com.caio.barbearia.enums.HorarioEnum;
import com.caio.barbearia.repositories.AgendamentoRepository;
import com.caio.barbearia.repositories.JornadaTrabalhoRepository;

@Service
public class HorarioService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private JornadaTrabalhoRepository jornadaTrabalhoRepository;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public HorarioService(AgendamentoRepository agendamentoRepository, JornadaTrabalhoRepository jornadaTrabalhoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.jornadaTrabalhoRepository = jornadaTrabalhoRepository;
    }

    public List<String> getHorariosDisponiveis(Long idFuncionario, LocalDate data) {
        // Busca os horários ocupados do banco
        List<String> horariosOcupados = agendamentoRepository
                .findHorariosOcupadosByFuncionarioAndData(idFuncionario, data);

        // Busca a jornada de trabalho do funcionário
        JornadaTrabalho jornada = jornadaTrabalhoRepository.findByFuncionarioId(idFuncionario)
                .orElseThrow(() -> new RuntimeException("Jornada de trabalho não encontrada"));

        // Pega todos os horários fixos
        List<String> todosHorarios = HorarioEnum.getTodosHorarios();

        // Filtra os horários disponíveis
        return todosHorarios.stream()
                .filter(horario -> !horariosOcupados.contains(horario)) // Remove horários ocupados
                .filter(horario -> isHorarioDentroDaJornada(horario, jornada)) // Remove horários fora da jornada/intervalo
                .toList();
    
    }

    private boolean isHorarioDentroDaJornada(String horario, JornadaTrabalho jornada) {
       // Formata os horários da entidade JornadaTrabalho
        String inicio = jornada.getInicioJornada().format(TIME_FORMATTER);
        String fim = jornada.getFimJornada().format(TIME_FORMATTER);
        String inicioIntervalo = jornada.getInicioIntervalo().format(TIME_FORMATTER);
        String fimIntervalo = jornada.getFimIntervalo().format(TIME_FORMATTER);

        // Verifica se o horário está na jornada de trabalho, mas fora do intervalo
        return horario.compareTo(inicio) >= 0 
            && horario.compareTo(fim) <= 0 
            && !(horario.compareTo(inicioIntervalo) >= 0 && horario.compareTo(fimIntervalo) < 0);
        }
}
