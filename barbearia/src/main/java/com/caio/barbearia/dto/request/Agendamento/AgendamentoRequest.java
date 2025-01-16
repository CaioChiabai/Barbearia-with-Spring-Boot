package com.caio.barbearia.dto.request.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.enums.Status;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "14:30:00")
    private LocalTime horaInicio;

    private LocalDate data;
    private FuncionarioProcedimento funcionarioProcedimento;
    private Status status;

    public AgendamentoRequest(Optional<Cliente> entityCliente, Optional<FuncionarioProcedimento> entityFuncionarioProcedimento, LocalDate data, LocalTime horaInicio, Status status) {
        this.cliente = entityCliente.orElse(null);
        this.funcionarioProcedimento = entityFuncionarioProcedimento.orElse(null);
        this.horaInicio = horaInicio;
        this.data = data;
        this.status = status;
    }
}
