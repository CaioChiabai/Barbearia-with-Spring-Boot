package com.caio.barbearia.dto.request.JornadaTrabalho;

import java.time.LocalTime;
import java.util.Optional;

import com.caio.barbearia.entities.Funcionario;

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
public class JornadaTrabalhoRequest {

    private Funcionario funcionario;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "08:00:00")
    private LocalTime inicioJornada;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "18:00:00")
    private LocalTime fimJornada;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "11:00:00")
    private LocalTime inicioIntervalo;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "12:00:00")
    private LocalTime fimIntervalo;

    public JornadaTrabalhoRequest(Optional<Funcionario> entityFuncionario, LocalTime inicioJornada, LocalTime fimJornada, LocalTime inicioIntervalo, LocalTime fimIntervalo) {
        this.funcionario = entityFuncionario.orElse(null);
        this.inicioJornada = inicioJornada;
        this.fimJornada = fimJornada;
        this.inicioIntervalo = inicioIntervalo;
        this.fimIntervalo = fimIntervalo;
    }
}
