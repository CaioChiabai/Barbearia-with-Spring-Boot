package com.caio.barbearia.controllers;

import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.Procedimento;
import com.caio.barbearia.services.FuncionarioService;
import com.caio.barbearia.services.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Funcionario> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario findById(@PathVariable(value = "id") Long id){

        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario create(@RequestBody Funcionario funcionario){
        return service.create(funcionario);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario update(@RequestBody Funcionario funcionario){
        return service.update(funcionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
