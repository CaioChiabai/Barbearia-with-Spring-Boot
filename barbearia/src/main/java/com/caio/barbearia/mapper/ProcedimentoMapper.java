package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.Procedimento.ProcedimentoRequest;
import com.caio.barbearia.dto.response.Procedimento.ProcedimentoResponse;
import com.caio.barbearia.entities.Procedimento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProcedimentoMapper {

    private final ModelMapper mapper;

    public Procedimento toProcedimento(ProcedimentoRequest request){
        return mapper.map(request, Procedimento.class);
    }

    public ProcedimentoResponse toProcedimentoResponse(Procedimento  procedimento){
        return mapper.map(procedimento, ProcedimentoResponse.class);
    }

    public List<ProcedimentoResponse> toProcedimentoResponseList(List<Procedimento> procedimento){
        return procedimento.stream()
                .map(this::toProcedimentoResponse)
                .collect(Collectors.toList());
    }

    public void updateProcedimentoFromRequest(ProcedimentoRequest request, Procedimento procedimento) {
        mapper.map(request, procedimento); 
    }
}
