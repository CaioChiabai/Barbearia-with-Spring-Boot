package com.caio.barbearia.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponse extends PessoaResponse{

    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;
}
