package com.caio.barbearia.dto.request.JornadaTrabalho;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JornadaTrabalhoMinRequest {

    private Long idfuncionario;

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
}
