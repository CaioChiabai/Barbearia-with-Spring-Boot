package com.caio.barbearia.services;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FuncionarioService {

    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    @Autowired
    FuncionarioRepository repository;

    public List<Funcionario> findAll(){
        logger.info("Procurando todos os funcionarios!");
        return repository.findAll();
    }

    public Funcionario findById(Long id){
        logger.info("Procurando um funcionario!");
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Funcionario create(Funcionario funcionario){
        logger.info("Criando um funcionario!");
        return repository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionario){
        logger.info("Atualizando um funcionario!");

        Funcionario entity = repository.findById(funcionario.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        entity.setNome(funcionario.getNome());
        entity.setSenha( funcionario.getSenha());
        entity.setEmail(funcionario.getEmail());
        entity.setCpf(funcionario.getCpf());
        entity.setCargo(funcionario.getCargo());;
        entity.setSalario(funcionario.getSalario());
        entity.setHoraEntrada(funcionario.getHoraEntrada());
        entity.setHoraSaida(funcionario.getHoraSaida());
        entity.setHoraAlmocoInicio(funcionario.getHoraAlmocoInicio());
        entity.setHoraAlmocoFim(funcionario.getHoraAlmocoFim());
        entity.setDataContratacao(funcionario.getDataContratacao());
        entity.setProcedimentos(funcionario.getProcedimentos());
        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deletando um funcionario!");

        Funcionario entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado esse ID!"));

        repository.delete(entity);
    }
}
