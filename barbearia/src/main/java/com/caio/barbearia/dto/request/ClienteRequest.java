package com.caio.barbearia.dto.request;

import com.caio.barbearia.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.caio.barbearia.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest extends PessoaRequest{

    private String telefone;
    private User user;
}
