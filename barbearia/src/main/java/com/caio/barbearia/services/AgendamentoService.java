package com.caio.barbearia.services;

import java.util.List;
import java.util.Optional;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoMinRequest;
import com.caio.barbearia.entities.*;
import com.caio.barbearia.repositories.ClienteRepository;
import com.caio.barbearia.repositories.FuncionarioProcedimentoRepository;
import com.caio.barbearia.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioProcedimentoRepository funcionarioProcedimentoRepository;

    public List<AgendamentoResponse> findAll() {
        List<Agendamento> entities = repository.findAll();
        return mapper.toAgendamentoResponseList(entities);
    }

    public AgendamentoResponse findById(Long id) {
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));
        return mapper.toAgendamentoResponse(entity);
    }

    public AgendamentoResponse create(AgendamentoMinRequest request) {
        Optional<Cliente> entityCliente = clienteRepository.findById(request.getIdCliente());
        Optional<FuncionarioProcedimento> entityFuncionarioProcedimento = funcionarioProcedimentoRepository.findById(request.getIdFuncionarioProcedimento());

        AgendamentoRequest newRequest = new AgendamentoRequest(entityCliente, entityFuncionarioProcedimento, request.getData(), request.getHoraInicio(), request.getStatus());

        Agendamento entity = mapper.toAgendamento(newRequest);
        Agendamento savedEntity = repository.save(entity);
        return mapper.toAgendamentoResponse(savedEntity);
    }

    public AgendamentoResponse update(Long id, AgendamentoMinRequest request) {
        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com o ID: " + id));

        Optional<Cliente> entityCliente = clienteRepository.findById(request.getIdCliente());
        Optional<FuncionarioProcedimento> entityFuncionarioProcedimento = funcionarioProcedimentoRepository.findById(request.getIdFuncionarioProcedimento());

        AgendamentoRequest newRequest = new AgendamentoRequest(entityCliente, entityFuncionarioProcedimento, request.getData(), request.getHoraInicio(), request.getStatus());

        mapper.updateAgendamentoFromRequest(newRequest, entity);
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
