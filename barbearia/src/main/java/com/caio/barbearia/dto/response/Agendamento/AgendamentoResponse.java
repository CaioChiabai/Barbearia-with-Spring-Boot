package com.caio.barbearia.dto.response.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

import com.caio.barbearia.dto.response.Cliente.ClienteResponse;
import com.caio.barbearia.dto.response.FuncionarioProcedimento.FuncionarioProcedimentoResponse;
import com.caio.barbearia.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoResponse {

    private Long id;
    private ClienteResponse cliente;
    private LocalTime horaInicio;
    private LocalDate data;
    private FuncionarioProcedimentoResponse funcionarioProcedimento;
    private Status status;
}
