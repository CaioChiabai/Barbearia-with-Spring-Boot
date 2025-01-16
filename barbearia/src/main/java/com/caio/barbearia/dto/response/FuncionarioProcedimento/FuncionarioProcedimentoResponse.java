package com.caio.barbearia.dto.response.FuncionarioProcedimento;

import com.caio.barbearia.dto.response.Funcionario.FuncionarioMinResponse;
import com.caio.barbearia.entities.Procedimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioProcedimentoResponse {

    private Long id;
    private FuncionarioMinResponse funcionario;
    private Procedimento procedimento;
}
