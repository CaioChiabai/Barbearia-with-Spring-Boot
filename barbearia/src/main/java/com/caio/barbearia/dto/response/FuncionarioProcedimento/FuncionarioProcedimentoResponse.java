package com.caio.barbearia.dto.response.FuncionarioProcedimento;

import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
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
    private FuncionarioResponse funcionario;
    private Procedimento procedimento;
}
