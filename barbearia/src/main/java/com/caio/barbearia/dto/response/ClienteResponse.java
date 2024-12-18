package com.caio.barbearia.dto.response;

import com.caio.barbearia.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse extends PessoaResponse{

    private String telefone;
    private User user;
}
