package com.caio.barbearia.dto.request;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.Procedimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioProcedimentoRequest {

    private Funcionario funcionario;
    private Procedimento procedimento;
}
