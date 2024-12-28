package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.ClienteResponse;
import com.caio.barbearia.dto.response.FuncionarioResponse;
import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.ClienteMapper;
import com.caio.barbearia.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    @Autowired
    private ClienteMapper mapper;

    public List<ClienteResponse> findAll() {
        List<Cliente> entities = repository.findAll();
        return mapper.toClienteResponseList(entities);
    }

    public ClienteResponse findById(Long id) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
        return mapper.toClienteResponse(entity);
    }

    public ClienteResponse findClienteByUserId(String userId) {
        Cliente entity = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado para o user ID: " + userId));
        return mapper.toClienteResponse(entity);
    }

    public ClienteResponse create(ClienteRequest request) {
        Cliente entity = mapper.toCliente(request);
        Cliente savedEntity = repository.save(entity);
        return mapper.toClienteResponse(savedEntity);
    }

    public ClienteResponse update(Long id, ClienteRequest request) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + id));
        mapper.updateClienteFromRequest(request, entity);
        Cliente updatedEntity = repository.save(entity);
        return mapper.toClienteResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
