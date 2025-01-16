package com.caio.barbearia.dto.response.Funcionario;

import com.caio.barbearia.dto.response.Pessoa.PessoaMinResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioMinResponse extends PessoaMinResponse {

    private String cargo;

}
