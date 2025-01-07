package com.caio.barbearia.dto.response.JornadaTrabalho;

import java.time.LocalTime;

import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JornadaTrabalhoResponse {
    
    private Long id;
    private FuncionarioResponse funcionario;
    private LocalTime inicioJornada;
    private LocalTime fimJornada;
    private LocalTime inicioIntervalo;
    private LocalTime fimIntervalo;
}
