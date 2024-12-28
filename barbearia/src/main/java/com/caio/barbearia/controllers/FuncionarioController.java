package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.services.AgendamentoService;
import com.caio.barbearia.services.FuncionarioService;

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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/funcionario")
@Tag(name = "Funcionário", description = "Endpoints para gerenciar funcionários")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todos os funcionários", 
               description = "Retorna uma lista de todos os funcionários cadastrados",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = FuncionarioResponse.class)))),
                   @ApiResponse(description = "Nenhum funcionário encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<FuncionarioResponse>> findAll() {
        List<FuncionarioResponse> response = service.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar funcionário por ID", 
               description = "Busca um funcionário específico pelo ID",
               responses = {
                   @ApiResponse(description = "Funcionário encontrado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = FuncionarioResponse.class))),
                   @ApiResponse(description = "Funcionário não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioResponse> findById(@PathVariable(value = "id") Long id) {
        // Validação para verificar se usuario autenticado é o mesmo que está tentando acessar
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser.getRole() == UserRole.FUNCIONARIO) {
            FuncionarioResponse funcionarioResponse = service.findFuncionarioByUserId(authenticatedUser.getId());
            if (funcionarioResponse == null || !funcionarioResponse.getId().equals(id)) {
                throw new AccessDeniedException("Acesso negado: você só pode acessar seus próprios dados.");
            }
        }

        //ADMIN pode ver de qualquer funcionario
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar um novo funcionário", 
               description = "Cria um novo funcionário com base nos dados fornecidos",
               responses = {
                   @ApiResponse(description = "Funcionário criado com sucesso", responseCode = "201",
                       content = @Content(schema = @Schema(implementation = FuncionarioResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioResponse> create(@RequestBody FuncionarioRequest funcionarioRequest) {
        try {
            FuncionarioResponse response = service.create(funcionarioRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar funcionário", 
               description = "Atualiza as informações de um funcionário existente",
               responses = {
                   @ApiResponse(description = "Funcionário atualizado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = FuncionarioResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Funcionário não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioResponse> update(@PathVariable Long id, @RequestBody FuncionarioRequest funcionarioRequest) {
        FuncionarioResponse response = service.update(id, funcionarioRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar funcionário", 
               description = "Remove um funcionário pelo ID",
               responses = {
                   @ApiResponse(description = "Funcionário removido com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Funcionário não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/agendamento")
    @Operation(summary = "Listar agendamentos por funcionário", 
               description = "Retorna uma lista de agendamentos associados a um funcionário específico",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = AgendamentoResponse.class)))),
                   @ApiResponse(description = "Nenhum agendamento encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Funcionário não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamentos(@PathVariable Long id) {
         // Validação para verificar se usuario autenticado é o mesmo que está tentando acessar
         User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (authenticatedUser.getRole() == UserRole.FUNCIONARIO) {
             FuncionarioResponse funcionarioResponse = service.findFuncionarioByUserId(authenticatedUser.getId());
             if (funcionarioResponse == null || !funcionarioResponse.getId().equals(id)) {
                 throw new AccessDeniedException("Acesso negado: você só pode acessar seus próprios dados.");
             }
         }
        
        List<AgendamentoResponse> agendamentos = agendamentoService.findByFuncionarioId(id);
        return ResponseEntity.ok(agendamentos);
    }
    
}
