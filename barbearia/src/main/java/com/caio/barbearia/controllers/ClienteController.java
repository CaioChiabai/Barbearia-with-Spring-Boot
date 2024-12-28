package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.AgendamentoResponse;
import com.caio.barbearia.dto.response.ClienteResponse;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.services.AgendamentoService;
import com.caio.barbearia.services.ClienteService;

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
@RequestMapping(value = "/cliente")
@Tag(name = "Cliente", description = "Endpoints para gerenciar clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todos os clientes", 
               description = "Retorna uma lista de todos os clientes cadastrados",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClienteResponse.class)))),
                   @ApiResponse(description = "Nenhum cliente encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteResponse> response = service.findAll();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar cliente por ID", 
               description = "Busca um cliente específico pelo ID",
               responses = {
                   @ApiResponse(description = "Cliente encontrado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
                   @ApiResponse(description = "Cliente não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ClienteResponse> findById(@PathVariable(value = "id") Long id) {
        // Obtém o usuário autenticado do contexto de segurança
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Validação para verificar se usuario autenticado é o mesmo que está tentando acessar
        if (authenticatedUser.getRole() == UserRole.CLIENTE) {
            ClienteResponse clienteResponse = service.findClienteByUserId(authenticatedUser.getId());
            if (clienteResponse == null || !clienteResponse.getId().equals(id)) {
                throw new AccessDeniedException("Acesso negado: você só pode acessar seus próprios dados.");
            }
        }
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar um novo cliente", 
               description = "Cria um novo cliente com base nos dados fornecidos",
               responses = {
                   @ApiResponse(description = "Cliente criado com sucesso", responseCode = "201",
                       content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest clienteRequest) {
        try {
            ClienteResponse response = service.create(clienteRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar cliente", 
               description = "Atualiza as informações de um cliente existente",
               responses = {
                   @ApiResponse(description = "Cliente atualizado com sucesso", responseCode = "200",
                       content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Cliente não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<ClienteResponse> update(@PathVariable Long id, @RequestBody ClienteRequest clienteRequest) {
        // Obtém o usuário autenticado do contexto de segurança
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Valida se o usuário autenticado é um cliente e garante que ele só pode atualizar seus próprios dados
        if (authenticatedUser.getRole() == UserRole.CLIENTE) {
            ClienteResponse clienteResponse = service.findClienteByUserId(authenticatedUser.getId());
            if (clienteResponse == null || !clienteResponse.getId().equals(id)) {
                throw new AccessDeniedException("Acesso negado: você só pode atualizar seus próprios dados.");
            }
        }

        ClienteResponse response = service.update(id, clienteRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar cliente", 
               description = "Remove um cliente pelo ID",
               responses = {
                   @ApiResponse(description = "Cliente removido com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Cliente não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        // Obtém o usuário autenticado do contexto de segurança
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Valida se o usuário autenticado é um cliente e garante que ele só pode atualizar seus próprios dados
        if (authenticatedUser.getRole() == UserRole.CLIENTE) {
            ClienteResponse clienteResponse = service.findClienteByUserId(authenticatedUser.getId());
            if (clienteResponse == null || !clienteResponse.getId().equals(id)) {
                throw new AccessDeniedException("Acesso negado: você só pode deletar seus próprios dados.");
            }
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/agendamento", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar agendamentos do cliente", 
               description = "Retorna uma lista de todos os agendamentos relacionados a um cliente",
               responses = {
                   @ApiResponse(description = "Agendamentos encontrados com sucesso", responseCode = "200",
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = AgendamentoResponse.class)))),
                   @ApiResponse(description = "Nenhum agendamento encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Cliente não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamentos(@PathVariable Long id) {
        // Obtém o usuário autenticado do contexto de segurança
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Valida se o usuário autenticado é um cliente e garante que ele só pode atualizar seus próprios dados
        if (authenticatedUser.getRole() == UserRole.CLIENTE) {
            ClienteResponse clienteResponse = service.findClienteByUserId(authenticatedUser.getId());
            if (clienteResponse == null || !clienteResponse.getId().equals(id)) {
                throw new AccessDeniedException("Acesso negado: você só pode acessar seus próprios dados.");
            }
        }

        List<AgendamentoResponse> response = agendamentoService.findByClienteId(id);
        return ResponseEntity.ok(response);
    }
    
}
