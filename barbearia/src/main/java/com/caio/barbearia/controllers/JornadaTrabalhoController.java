package com.caio.barbearia.controllers;

import java.util.List;

import com.caio.barbearia.dto.request.JornadaTrabalho.JornadaTrabalhoMinRequest;
import com.caio.barbearia.dto.request.JornadaTrabalho.JornadaTrabalhoRequest;
import com.caio.barbearia.dto.response.JornadaTrabalho.JornadaTrabalhoResponse;
import com.caio.barbearia.services.JornadaTrabalhoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/jornada_trabalho")@Tag(name = "Jornada de Trabalho", description = "Endpoints para gerenciar jornadas de trabalho")
public class JornadaTrabalhoController {

    @Autowired
    private JornadaTrabalhoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todas as jornadas de trabalho", 
               description = "Retorna uma lista de todas as jornadas de trabalho",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = JornadaTrabalhoResponse.class)))),
                   @ApiResponse(description = "Nenhuma jornada encontrada", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<JornadaTrabalhoResponse>> findAll() {
        List<JornadaTrabalhoResponse> response = service.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar jornada de trabalho por ID",
               description = "Busca uma jornada de trabalho específica pelo ID",
               responses = {
                   @ApiResponse(description = "Jornada encontrada com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = JornadaTrabalhoResponse.class))),
                   @ApiResponse(description = "Jornada não encontrada", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<JornadaTrabalhoResponse> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar nova jornada de trabalho",
               description = "Cria uma nova jornada de trabalho",
               responses = {
                   @ApiResponse(description = "Jornada criada com sucesso", responseCode = "201",
                       content = @Content(schema = @Schema(implementation = JornadaTrabalhoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<JornadaTrabalhoResponse> create(@RequestBody JornadaTrabalhoMinRequest request) {
        JornadaTrabalhoResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar jornada de trabalho",
               description = "Atualiza uma jornada de trabalho existente pelo ID",
               responses = {
                   @ApiResponse(description = "Jornada atualizada com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = JornadaTrabalhoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Jornada não encontrada", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<JornadaTrabalhoResponse> update(@PathVariable(value = "id") Long id, 
                                                          @RequestBody JornadaTrabalhoMinRequest request) {
        JornadaTrabalhoResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar jornada de trabalho",
               description = "Remove uma jornada de trabalho pelo ID",
               responses = {
                   @ApiResponse(description = "Jornada removida com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Jornada não encontrada", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
