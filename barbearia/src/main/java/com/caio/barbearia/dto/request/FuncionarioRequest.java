package com.caio.barbearia.dto.request;

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
public class FuncionarioRequest extends PessoaRequest{

    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;
}
