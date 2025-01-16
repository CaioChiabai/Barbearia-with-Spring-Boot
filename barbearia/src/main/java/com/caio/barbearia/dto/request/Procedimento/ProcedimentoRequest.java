package com.caio.barbearia.dto.request.Procedimento;

import java.time.LocalTime;

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
public class ProcedimentoRequest {

    private String nome;
    private Double preco;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "00:30:00")
    private LocalTime duracao;
}
