package com.caio.barbearia.services;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.JornadaTrabalho;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.FuncionarioRepository;
import com.caio.barbearia.repositories.JornadaTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class JornadaTrabalhoService {

    private Logger logger = Logger.getLogger(JornadaTrabalhoService.class.getName());

    @Autowired
    JornadaTrabalhoRepository repository;

    public List<JornadaTrabalho> findAll(){
        logger.info("Procurando todos as jornadas de trabalhos!");
        return repository.findAll();
    }

    public JornadaTrabalho findById(Long id){
        logger.info("Procurando uma jornada de trabalho!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID"));
    }

    public JornadaTrabalho create(JornadaTrabalho jornadaTrabalho){
        logger.info("Criando uma jornada de trabalho!");
        return repository.save(jornadaTrabalho);
    }

    public JornadaTrabalho update(JornadaTrabalho jornadaTrabalho){
        logger.info("Atualizando uma jornada de trabalho!");

        JornadaTrabalho entity = repository.findById(jornadaTrabalho.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setFuncionario(jornadaTrabalho.getFuncionario());
        entity.setInicioJornada(jornadaTrabalho.getInicioJornada());
        entity.setFimJornada(jornadaTrabalho.getFimJornada());
        entity.setInicioIntervalo(jornadaTrabalho.getInicioIntervalo());
        entity.setFimIntervalo(jornadaTrabalho.getFimIntervalo());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando uma jornada de trabalho!");

        JornadaTrabalho entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }
}
