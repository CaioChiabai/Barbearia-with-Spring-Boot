package com.caio.barbearia.dto.request.Agendamento;

import com.caio.barbearia.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoMinRequest {

    private Long idCliente;
    private LocalTime horaInicio;
    private LocalDate data;
    private Long idFuncionarioProcedimento;
    private Status status;
}
