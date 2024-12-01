package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.FuncionarioProcedimentoRequest;
import com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse;
import com.caio.barbearia.entities.FuncionarioProcedimento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FuncionarioProcedimentoMapper {

    private final ModelMapper mapper;

    public FuncionarioProcedimento toFuncionarioProcedimento(FuncionarioProcedimentoRequest request){
        return mapper.map(request, FuncionarioProcedimento.class);
    }

    public FuncionarioProcedimentoResponse toFuncionarioProcedimentoResponse(FuncionarioProcedimento  status){
        return mapper.map(status, FuncionarioProcedimentoResponse.class);
    }

    public List<FuncionarioProcedimentoResponse> toFuncionarioProcedimentoResponseList(List<FuncionarioProcedimento> status){
        return status.stream()
                .map(this::toFuncionarioProcedimentoResponse)
                .collect(Collectors.toList());
    }

    public void updateFuncionarioProcedimentoFromRequest(FuncionarioProcedimentoRequest request, FuncionarioProcedimento funcionarioProcedimento) {
        mapper.map(request, funcionarioProcedimento); 
    }
}
