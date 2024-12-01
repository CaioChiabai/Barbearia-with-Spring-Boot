package com.caio.barbearia.services;

import java.util.List;
import java.util.logging.Logger;

import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.ProcedimentoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.dto.request.ProcedimentoRequest;
import com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse;
import com.caio.barbearia.dto.response.ProcedimentoResponse;
import com.caio.barbearia.entities.FuncionarioProcedimento;
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

    @Autowired
    private ProcedimentoMapper mapper;

    public List<ProcedimentoResponse> findAll(){       
        logger.info("Procurando todos os procedimentos!");
        List<Procedimento> entities = repository.findAll();
        return  mapper.toProcedimentoResponseList(entities); 
    }

    public List<FuncionarioProcedimentoResponse> findFuncionariosByProcedimentoId(Long procedimentoId) {
         return funcionarioProcedimentoRepository.findFuncionariosByProcedimentoId(procedimentoId);
    }

    public ProcedimentoResponse findById(Long id){
        logger.info("Procurando um procedimento!");
        Procedimento entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return mapper.toProcedimentoResponse(entity);
    }

    public ProcedimentoResponse create(ProcedimentoRequest procedimentoRequest){
        logger.info("Criando um procedimento!");
        Procedimento entity = mapper.toProcedimento(procedimentoRequest);
        Procedimento createdProcedimento = repository.save(entity);
        return  mapper.toProcedimentoResponse(createdProcedimento);
        
    }

    public ProcedimentoResponse update(Long id, ProcedimentoRequest procedimentoRequest){
        logger.info("Atualizando um procedimento!");

        Procedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id));
        mapper.updateProcedimentoFromRequest(procedimentoRequest, entity); 
        Procedimento updatedEntity = repository.save(entity);
        return mapper.toProcedimentoResponse(updatedEntity);
    }

    public void delete(Long id){
        logger.info("Deletando um procedimento!");

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

}
