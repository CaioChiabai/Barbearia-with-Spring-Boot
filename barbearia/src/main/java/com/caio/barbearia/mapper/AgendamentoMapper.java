package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.AgendamentoRequest;
import com.caio.barbearia.dto.response.AgendamentoResponse;
import com.caio.barbearia.entities.Agendamento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendamentoMapper {

    private final ModelMapper mapper;

    public Agendamento toAgendamento(AgendamentoRequest request){
        return mapper.map(request, Agendamento.class);
    }

    public AgendamentoResponse toAgendamentoResponse(Agendamento  agendamento){
        return mapper.map(agendamento, AgendamentoResponse.class);
    }

    public List<AgendamentoResponse> toAgendamentoResponseList(List<Agendamento> agendamentos){
        return agendamentos.stream()
                .map(this::toAgendamentoResponse)
                .collect(Collectors.toList());
    }

    public void updateAgendamentoFromRequest(AgendamentoRequest request, Agendamento agendamento) {
        mapper.map(request, agendamento); 
    }
}
