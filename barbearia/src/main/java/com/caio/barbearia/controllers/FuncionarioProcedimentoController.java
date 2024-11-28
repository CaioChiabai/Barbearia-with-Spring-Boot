package com.caio.barbearia.controllers;

import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.services.FuncionarioProcedimentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario_procedimento")
public class FuncionarioProcedimentoController {

    @Autowired
    private FuncionarioProcedimentoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FuncionarioProcedimento> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FuncionarioProcedimento findById(@PathVariable(value = "id") Long id){

        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FuncionarioProcedimento create(@RequestBody FuncionarioProcedimento funcionarioProcedimento){
        return service.create(funcionarioProcedimento);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FuncionarioProcedimento update(@RequestBody FuncionarioProcedimento funcionarioProcedimento){
        return service.update(funcionarioProcedimento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
