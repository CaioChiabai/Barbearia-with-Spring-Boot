package com.caio.barbearia.dto.request.FuncionarioProcedimento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioProcedimentoMinRequest {

    private Long idFuncionario;
    private Long idProcedimento;
}
