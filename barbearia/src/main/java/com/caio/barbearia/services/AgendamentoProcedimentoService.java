package com.caio.barbearia.services;

import com.caio.barbearia.dto.AgendamentoProcedimentoDTO;
import com.caio.barbearia.entities.AgendamentoProcedimento;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.AgendamentoProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AgendamentoProcedimentoService {

    private Logger logger = Logger.getLogger(AgendamentoProcedimentoService.class.getName());

    @Autowired
    AgendamentoProcedimentoRepository repository;

    public List<AgendamentoProcedimento> findAll(){
        logger.info("Procurando todos os agendamentos!");
        return repository.findAll();
    }

    public AgendamentoProcedimento findById(Long id){
        logger.info("Procurando um agendamento!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public AgendamentoProcedimento create(AgendamentoProcedimento agendamentoProcedimento){
        logger.info("Criando um agendamento!");
        return repository.save(agendamentoProcedimento);
    }

    public AgendamentoProcedimento update(AgendamentoProcedimento agendamentoProcedimento){
        logger.info("Atualizando um agendamento!");

        AgendamentoProcedimento entity = repository.findById(agendamentoProcedimento.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setAgendamento(agendamentoProcedimento.getAgendamento());
        entity.setFuncionarioProcedimento(agendamentoProcedimento.getFuncionarioProcedimento());
        entity.setQuantidade(agendamentoProcedimento.getQuantidade());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um cliente!");

        AgendamentoProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }

    public List<AgendamentoProcedimentoDTO> findAllByFuncionario(Long idFuncionario){
        return repository.findAllByFuncionarioId(idFuncionario);
    }
}
