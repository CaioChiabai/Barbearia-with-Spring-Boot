package com.caio.barbearia.dto.request.Agendamento;

import com.caio.barbearia.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "14:30:00")
    private LocalTime horaInicio;

    private LocalDate data;
    private Long idFuncionarioProcedimento;
    private Status status;
}
