package com.caio.barbearia.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.caio.barbearia.dto.RegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.Cliente.ClienteResponse;
import com.caio.barbearia.entities.Cliente;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final ModelMapper mapper;

    public ClienteResponse toClienteResponse(Cliente  cliente){
        return mapper.map(cliente, ClienteResponse.class);
    }

    public List<ClienteResponse> toClienteResponseList(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toClienteResponse)
                .collect(Collectors.toList());
    }

    public void updateClienteFromRequest(ClienteRequest request, Cliente cliente) {
        mapper.map(request, cliente); 
    }
}
