package com.caio.barbearia.dto.request.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.caio.barbearia.dto.request.User.UserMinRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.caio.barbearia.dto.request.Pessoa.PessoaRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequest extends PessoaRequest{

    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;
    private UserMinRequest User;
}
