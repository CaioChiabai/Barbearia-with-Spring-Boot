package com.caio.barbearia.dto.response.Cliente;

import com.caio.barbearia.dto.response.Pessoa.PessoaMinResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteMinResponse extends PessoaMinResponse {

    private String telefone;

    public ClienteMinResponse(long id, String nome, String telefone) {
        this.setId(id);
        this.setNome(nome);
        this.setTelefone(telefone);
    }
}
