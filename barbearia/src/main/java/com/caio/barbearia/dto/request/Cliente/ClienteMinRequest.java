package com.caio.barbearia.dto.request.Cliente;

import com.caio.barbearia.dto.request.Pessoa.PessoaMinRequest;
import com.caio.barbearia.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteMinRequest extends PessoaMinRequest {
    
    private String telefone;
    private User user;
    
}
