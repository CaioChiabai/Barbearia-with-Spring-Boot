package com.caio.barbearia.controllers;

import java.util.List;

import com.caio.barbearia.entities.JornadaTrabalho;
import com.caio.barbearia.services.JornadaTrabalhoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "/jornada_trabalho")
public class JornadaTrabalhoController {

    @Autowired
    private JornadaTrabalhoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JornadaTrabalho> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JornadaTrabalho findById(@PathVariable(value = "id") Long id){

        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JornadaTrabalho create(@RequestBody JornadaTrabalho jornadaTrabalho){
        return service.create(jornadaTrabalho);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JornadaTrabalho update(@RequestBody JornadaTrabalho jornadaTrabalho){
        return service.update(jornadaTrabalho);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
