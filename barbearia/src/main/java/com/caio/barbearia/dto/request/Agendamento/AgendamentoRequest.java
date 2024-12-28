package com.caio.barbearia.dto.request.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoRequest {

    private Cliente cliente;
    private LocalTime horaInicio;
    private LocalDate data;
    private FuncionarioProcedimento funcionarioProcedimento;
    private Status status;
}
