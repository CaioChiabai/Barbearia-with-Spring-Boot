package com.caio.barbearia.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.dto.AgendamentoDTO;
import com.caio.barbearia.entities.Agendamento;
import com.caio.barbearia.enums.Status;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

    private Logger logger = Logger.getLogger(AgendamentoService.class.getName());

    @Autowired
    AgendamentoRepository repository;

    public List<Agendamento> findAll(){
        logger.info("Procurando todos os agendamentos!");
        return repository.findAll();
    }

    public Agendamento findById(Long id){
        logger.info("Procurando um agendamento!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Agendamento create(Agendamento agendamento){
        logger.info("Criando um agendamento!");
        Status statusEmAberto = statusRepository.findByDescricao("Em aberto")
                .orElseThrow(() -> new IllegalArgumentException("Status 'Em aberto' não encontrado"));

        agendamento.setStatus(statusEmAberto);
        return repository.save(agendamento);
    }

    public Agendamento update(Agendamento agendamento){
        logger.info("Atualizando um agendamento!");

        Agendamento entity = repository.findById(agendamento.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setCliente(agendamento.getCliente());
        entity.setData(agendamento.getData());
        entity.setHoraInicio(agendamento.getHoraInicio());
        entity.setFuncionarioProcedimento(agendamento.getFuncionarioProcedimento());
        entity.setStatus(agendamento.getStatus());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um cliente!");

        Agendamento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }

    public List<AgendamentoDTO> findByFuncionarioId(Long idFuncionario){
        return repository.findByFuncionarioId(idFuncionario);
    }
    
    public List<AgendamentoDTO> findByClienteId(Long idCliente){
        return repository.findByClienteId(idCliente);
    }
}
