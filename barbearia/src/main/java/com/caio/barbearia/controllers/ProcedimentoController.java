package com.caio.barbearia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.caio.barbearia.dto.request.ProcedimentoRequest;
import com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse;
import com.caio.barbearia.dto.response.ProcedimentoResponse;
import com.caio.barbearia.entities.Procedimento;
import com.caio.barbearia.services.ProcedimentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/procedimento")
@Tag(name = "Procedimento", description = "Gerenciamento de procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Procurar todos os procedimentos", 
               description = "Retorna uma lista de todos os procedimentos cadastrados",
               responses = {
                   @ApiResponse(description = "Lista de procedimentos encontrada", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProcedimentoResponse.class)))),
                   @ApiResponse(description = "Nenhum procedimento encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<ProcedimentoResponse>> findAll() {
        List<ProcedimentoResponse> procedimentos = service.findAll();
        if (procedimentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(procedimentos);
    }

    @GetMapping(value = "/{id}/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar funcionários vinculados a um procedimento", 
               description = "Retorna uma lista de funcionários vinculados ao procedimento especificado pelo ID",
               responses = {
                   @ApiResponse(description = "Lista de funcionários encontrada", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FuncionarioProcedimentoResponse.class)))),
                   @ApiResponse(description = "Procedimento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<FuncionarioProcedimentoResponse>> findFuncionariosByProcedimentoId(@PathVariable Long id) {
        List<FuncionarioProcedimentoResponse> funcionarios = service.findFuncionariosByProcedimentoId(id);
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar um procedimento por ID", 
               description = "Retorna o procedimento correspondente ao ID fornecido",
               responses = {
                   @ApiResponse(description = "Procedimento encontrado", responseCode = "200", content = @Content(schema = @Schema(implementation = Procedimento.class))),
                   @ApiResponse(description = "Procedimento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ProcedimentoResponse> findById(@PathVariable(value = "id") Long id) {
        ProcedimentoResponse procedimento = service.findById(id);
        if (procedimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(procedimento);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar um novo procedimento", 
               description = "Cria um novo procedimento com base nos dados fornecidos",
               responses = {
                   @ApiResponse(description = "Procedimento criado com sucesso", responseCode = "201", content = @Content(schema = @Schema(implementation = ProcedimentoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ProcedimentoResponse> create(@RequestBody ProcedimentoRequest procedimentoRequest) {
        ProcedimentoResponse createdProcedimento = service.create(procedimentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcedimento);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar um procedimento existente", 
               description = "Atualiza os dados de um procedimento com base no ID fornecido",
               responses = {
                   @ApiResponse(description = "Procedimento atualizado com sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ProcedimentoResponse.class))),
                   @ApiResponse(description = "Procedimento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ProcedimentoResponse> update(@PathVariable Long id, @RequestBody ProcedimentoRequest procedimentoRequest) {
        ProcedimentoResponse updatedProcedimento = service.update(id, procedimentoRequest);
        if (updatedProcedimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProcedimento);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Excluir um procedimento", 
               description = "Exclui um procedimento com base no ID fornecido",
               responses = {
                   @ApiResponse(description = "Procedimento excluído com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Procedimento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                   @ApiResponse(description = "Proibido", responseCode = "403", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
