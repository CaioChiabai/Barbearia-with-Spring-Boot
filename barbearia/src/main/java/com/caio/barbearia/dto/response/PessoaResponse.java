package com.caio.barbearia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

    private Long id;
    private String nome;
    private String senha;
    private String cpf;
    private String email;
}
