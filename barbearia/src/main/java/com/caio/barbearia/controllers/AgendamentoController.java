package com.caio.barbearia.controllers;

import java.util.List;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoMinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.services.AgendamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/agendamento")
@Tag(name = "Agendamento", description = "Endpoints para gerenciar agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todos os agendamentos", 
               description = "Retorna uma lista de todos os agendamentos cadastrados",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = AgendamentoResponse.class)))),
                   @ApiResponse(description = "Nenhum agendamento encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<AgendamentoResponse>> findAll() {
        List<AgendamentoResponse> response = service.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar agendamento por ID", 
               description = "Busca um agendamento específico pelo ID",
               responses = {
                   @ApiResponse(description = "Agendamento encontrado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = AgendamentoResponse.class))),
                   @ApiResponse(description = "Agendamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<AgendamentoResponse> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar um novo agendamento", 
               description = "Cria um novo agendamento com base nos dados fornecidos",
               responses = {
                   @ApiResponse(description = "Agendamento criado com sucesso", responseCode = "201",
                       content = @Content(schema = @Schema(implementation = AgendamentoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<AgendamentoResponse> create(@RequestBody AgendamentoMinRequest agendamentoRequest) {
        try {
            AgendamentoResponse response = service.create(agendamentoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar agendamento", 
               description = "Atualiza as informações de um agendamento existente",
               responses = {
                   @ApiResponse(description = "Agendamento atualizado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = AgendamentoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Agendamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<AgendamentoResponse> update(@PathVariable Long id, @RequestBody AgendamentoMinRequest agendamentoRequest) {
        AgendamentoResponse response = service.update(id, agendamentoRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar agendamento", 
               description = "Remove um agendamento pelo ID",
               responses = {
                   @ApiResponse(description = "Agendamento removido com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Agendamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
