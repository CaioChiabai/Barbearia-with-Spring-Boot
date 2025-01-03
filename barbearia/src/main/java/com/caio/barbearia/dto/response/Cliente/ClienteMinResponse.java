package com.caio.barbearia.dto.response.Cliente;

import com.caio.barbearia.dto.response.Pessoa.PessoaMinResponse;
import com.caio.barbearia.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteMinResponse extends PessoaMinResponse{
    
    private String telefone;
    private User user;
}
