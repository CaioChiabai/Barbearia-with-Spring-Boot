package com.caio.barbearia.dto.response;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.Procedimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioProcedimentoResponse {

    private Long id;
    private Funcionario funcionario;
    private Procedimento procedimento;
}
