package com.caio.barbearia.services;

import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.FuncionarioProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FuncionarioProcedimentoService {

    private Logger logger = Logger.getLogger(FuncionarioProcedimentoService.class.getName());

    @Autowired
    FuncionarioProcedimentoRepository repository;

    public List<FuncionarioProcedimento> findAll(){
        logger.info("Procurando todos os funcionarios!");
        return repository.findAll();
    }

    public FuncionarioProcedimento findById(Long id){
        logger.info("Procurando um funcionario!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public FuncionarioProcedimento create(FuncionarioProcedimento funcionarioProcedimento){
        logger.info("Criando um funcionario!");
        return repository.save(funcionarioProcedimento);
    }

    public FuncionarioProcedimento update(FuncionarioProcedimento funcionarioProcedimento){
        logger.info("Atualizando um funcionario!");

        FuncionarioProcedimento entity = repository.findById(funcionarioProcedimento.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setFuncionario(funcionarioProcedimento.getFuncionario());
        entity.setProcedimento(funcionarioProcedimento.getProcedimento());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um funcionario!");

        FuncionarioProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }
}
