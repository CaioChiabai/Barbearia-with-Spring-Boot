package com.caio.barbearia.dto.request.FuncionarioProcedimento;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.Procedimento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioProcedimentoRequest {

    private Funcionario funcionario;
    private Procedimento procedimento;

    public FuncionarioProcedimentoRequest(Optional<Funcionario> entityFuncionario, Optional<Procedimento> entityProcedimento) {
        this.funcionario = entityFuncionario.orElse(null);
        this.procedimento = entityProcedimento.orElse(null);
    }
}
