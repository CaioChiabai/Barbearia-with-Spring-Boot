package com.caio.barbearia.dto.request.Cliente;

import com.caio.barbearia.dto.request.Pessoa.PessoaRequest;
import com.caio.barbearia.dto.request.User.UserMinRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest extends PessoaRequest{

    private String telefone;
}
