package com.caio.barbearia.dto.request.Cliente;

import com.caio.barbearia.dto.request.Pessoa.PessoaRequest;
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

    public ClienteRequest(String clienteAtualizado, String cpf, String telefone) {
        this.setNome(clienteAtualizado);
        this.setCpf(cpf);
        this.setTelefone(telefone);
    }
}
