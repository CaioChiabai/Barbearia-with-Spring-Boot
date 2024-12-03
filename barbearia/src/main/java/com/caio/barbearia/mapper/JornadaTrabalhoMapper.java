package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.JornadaTrabalhoRequest;
import com.caio.barbearia.dto.response.JornadaTrabalhoResponse;
import com.caio.barbearia.entities.JornadaTrabalho;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JornadaTrabalhoMapper {

     private final ModelMapper mapper;

    public JornadaTrabalho toJornadaTrabalho(JornadaTrabalhoRequest request){
        return mapper.map(request, JornadaTrabalho.class);
    }

    public JornadaTrabalhoResponse toJornadaTrabalhoResponse(JornadaTrabalho  jornadaTrabalho){
        return mapper.map(jornadaTrabalho, JornadaTrabalhoResponse.class);
    }

    public List<JornadaTrabalhoResponse> toJornadaTrabalhoResponseList(List<JornadaTrabalho> jornadaTrabalho){
        return jornadaTrabalho.stream()
                .map(this::toJornadaTrabalhoResponse)
                .collect(Collectors.toList());
    }

    public void updateJornadaTrabalhoFromRequest(JornadaTrabalhoRequest request, JornadaTrabalho jornadaTrabalho) {
        mapper.map(request, jornadaTrabalho); 
    }
}
