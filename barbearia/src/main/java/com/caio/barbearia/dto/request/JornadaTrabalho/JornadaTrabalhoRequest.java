package com.caio.barbearia.dto.request.JornadaTrabalho;

import java.time.LocalTime;

import com.caio.barbearia.entities.Funcionario;

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
    private LocalTime inicioJornada;
    private LocalTime fimJornada;
    private LocalTime inicioIntervalo;
    private LocalTime fimIntervalo;
}
