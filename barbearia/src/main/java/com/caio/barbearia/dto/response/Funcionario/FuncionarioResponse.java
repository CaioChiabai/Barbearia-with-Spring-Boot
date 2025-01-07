package com.caio.barbearia.dto.response.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.caio.barbearia.dto.response.Pessoa.PessoaResponse;
import com.caio.barbearia.dto.response.User.UserMinResponse;
import com.caio.barbearia.entities.User;
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
    private UserMinResponse user;
}
