package com.caio.barbearia.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.Status;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.ClienteRepository;
import com.caio.barbearia.repositories.StatusRepository;

@Service
public class StatusService {

    private Logger logger = Logger.getLogger(StatusService.class.getName());

    @Autowired
    StatusRepository repository;

    public List<Status> findAll(){
        logger.info("Procurando todos os status!");
        return repository.findAll();
    }

    public Status findById(Long id){
        logger.info("Procurando um status!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Status create(Status status){
        logger.info("Criando um status!");
        return repository.save(status);
    }

    public Status update(Status status){
        logger.info("Atualizando um cliente!");

        Status entity = repository.findById(status.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setDescricao(status.getDescricao());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um cliente!");

        Status entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }
}
