package com.caio.barbearia.services;

import com.caio.barbearia.dto.RegisterDTO;
import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.FuncionarioMapper;
import com.caio.barbearia.repositories.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    @Autowired
    FuncionarioRepository repository;

    @Autowired
    private FuncionarioMapper mapper;

    public List<FuncionarioResponse> findAll() {
        List<Funcionario> entities = repository.findAll();
        return mapper.toFuncionarioResponseList(entities);
    }

    public FuncionarioResponse findById(Long id) {
        Funcionario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));
        return mapper.toFuncionarioResponse(entity);
    }

    public FuncionarioResponse findFuncionarioByUserId(String userId) {
        Funcionario entity = repository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado para o user ID: " + userId));
        return mapper.toFuncionarioResponse(entity);
    }

    public void create(RegisterDTO request, User user) {
        Funcionario entity = new Funcionario(request.nome(), request.cpf(), user);
        repository.save(entity);
    }

    public FuncionarioResponse update(Long id, FuncionarioRequest request) {
        Funcionario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));
        mapper.updateFuncionarioFromRequest(request, entity);
        Funcionario updatedEntity = repository.save(entity);
        return mapper.toFuncionarioResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
