package com.caio.barbearia.services;

import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService {

    private Logger logger = Logger.getLogger(ClienteService.class.getName());

    @Autowired
    ClienteRepository repository;

    public List<Cliente> findAll(){
        logger.info("Procurando todos os cliente!");
        return repository.findAll();
    }

    public Cliente findById(Long id){
        logger.info("Procurando um cliente!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Cliente create(Cliente cliente){
        logger.info("Criando um cliente!");
        return repository.save(cliente);
    }

    public Cliente update(Cliente cliente){
        logger.info("Atualizando um cliente!");

        Cliente entity = repository.findById(cliente.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setNome(cliente.getNome());
        entity.setSenha( cliente.getSenha());
        entity.setEmail(cliente.getEmail());
        entity.setTelefone(cliente.getTelefone());
        entity.setCpf(cliente.getCpf());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um cliente!");

        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }
}
