package com.caio.barbearia.controllers;

import com.caio.barbearia.entities.AgendamentoProcedimento;
import com.caio.barbearia.services.AgendamentoProcedimentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/agendamento_procedimento")
public class AgendamentoProcedimentoController {

    @Autowired
    private AgendamentoProcedimentoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AgendamentoProcedimento> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AgendamentoProcedimento findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public AgendamentoProcedimento create(@RequestBody AgendamentoProcedimento agendamentoProcedimento){
        return service.create(agendamentoProcedimento);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AgendamentoProcedimento update(@RequestBody AgendamentoProcedimento agendamentoProcedimento){
        return service.update(agendamentoProcedimento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
