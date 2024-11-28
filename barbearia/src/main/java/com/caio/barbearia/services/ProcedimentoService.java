package com.caio.barbearia.services;

import java.util.List;
import java.util.logging.Logger;

import com.caio.barbearia.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.dto.FuncionarioProcedimentoDTO;
import com.caio.barbearia.dto.ProcedimentoView;
import com.caio.barbearia.entities.Procedimento;
import com.caio.barbearia.repositories.FuncionarioProcedimentoRepository;
import com.caio.barbearia.repositories.ProcedimentoRepository;

@Service
public class ProcedimentoService {

    private Logger logger = Logger.getLogger(ProcedimentoService.class.getName());

    @Autowired
    ProcedimentoRepository repository;

    @Autowired
    FuncionarioProcedimentoRepository funcionarioProcedimentoRepository;

    public List<ProcedimentoView> findAll(){       
        logger.info("Procurando todos os procedimentos!");
        return repository.findAllProjectedBy();
    }

    public List<FuncionarioProcedimentoDTO> findFuncionariosByProcedimentoId(Long procedimentoId) {
        return funcionarioProcedimentoRepository.findFuncionariosByProcedimentoId(procedimentoId);
    }

    public Procedimento findById(Long id){
        logger.info("Procurando um procedimento!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Procedimento create(Procedimento procedimento){
        logger.info("Criando um procedimento!");
        return repository.save(procedimento);
    }

    public Procedimento update(Procedimento procedimento){
        logger.info("Atualizando um procedimento!");

        Procedimento entity = repository.findById(procedimento.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setNome(procedimento.getNome());
        entity.setPreco(procedimento.getPreco());
        entity.setDuracao(procedimento.getDuracao());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um procedimento!");

        Procedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }

}
