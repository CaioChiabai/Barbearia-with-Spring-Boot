package com.caio.barbearia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.dto.request.AgendamentoRequest;
import com.caio.barbearia.dto.response.AgendamentoResponse;
import com.caio.barbearia.entities.Agendamento;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.AgendamentoMapper;
import com.caio.barbearia.repositories.AgendamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private AgendamentoMapper mapper;

    public List<AgendamentoResponse> findAll() {
        List<Agendamento> entities = repository.findAll();
        return mapper.toAgendamentoResponseList(entities);
    }

    public AgendamentoResponse findById(Long id) {
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));
        return mapper.toAgendamentoResponse(entity);
    }

    public AgendamentoResponse create(AgendamentoRequest request) {
        Agendamento entity = mapper.toAgendamento(request);
        Agendamento savedEntity = repository.save(entity);
        return mapper.toAgendamentoResponse(savedEntity);
    }

    public AgendamentoResponse update(Long id, AgendamentoRequest request) {
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));
        mapper.updateAgendamentoFromRequest(request, entity);
        Agendamento updatedEntity = repository.save(entity);
        return mapper.toAgendamentoResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<AgendamentoResponse> findByFuncionarioId(Long idFuncionario) {
        List<Agendamento> agendamentos = repository.findByFuncionarioId(idFuncionario);
        return mapper.toAgendamentoResponseList(agendamentos);
    }

    public List<AgendamentoResponse> findByClienteId(Long idCliente) {
        List<Agendamento> agendamentos = repository.findByClienteId(idCliente);
        return mapper.toAgendamentoResponseList(agendamentos);
    }

}
