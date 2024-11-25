package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.AgendamentoProcedimentoDTO;
import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.services.AgendamentoProcedimentoService;
import com.caio.barbearia.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private AgendamentoProcedimentoService agendamentoProcedimentoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cliente> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cliente findById(@PathVariable(value = "id") Long id){

        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
         try {
            Cliente novoCliente = service.create(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Cliente update(@RequestBody Cliente cliente){
        return service.update(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping(value = "/{id}/agendamento")
    public ResponseEntity<List<AgendamentoProcedimentoDTO>> listarAgendamentosProcedimentos(@PathVariable Long id) {
        List<AgendamentoProcedimentoDTO> agendamentos = agendamentoProcedimentoService.findByClienteId(id);
        return ResponseEntity.ok(agendamentos);
    }
    

}
