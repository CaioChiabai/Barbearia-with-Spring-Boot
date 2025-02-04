package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.caio.barbearia.dto.RegisterDTO;
import com.caio.barbearia.entities.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.Funcionario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FuncionarioMapper {

    private final ModelMapper mapper;

    public FuncionarioResponse toFuncionarioResponse(Funcionario  funcionario){
        return mapper.map(funcionario, FuncionarioResponse.class);
    }

    public List<FuncionarioResponse> toFuncionarioResponseList(List<Funcionario> funcionarios){
        return funcionarios.stream()
                .map(this::toFuncionarioResponse)
                .collect(Collectors.toList());
    }

    public void updateFuncionarioFromRequest(FuncionarioRequest request, Funcionario funcionario) {
        mapper.map(request, funcionario); 
    }
}
