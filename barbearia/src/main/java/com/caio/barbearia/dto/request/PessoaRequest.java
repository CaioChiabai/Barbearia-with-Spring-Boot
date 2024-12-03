package com.caio.barbearia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequest {

    private String nome;
    private String senha;
    private String cpf;
    private String email;
}
