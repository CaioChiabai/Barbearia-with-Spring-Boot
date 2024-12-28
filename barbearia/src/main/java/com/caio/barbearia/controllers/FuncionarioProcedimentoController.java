package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.FuncionarioProcedimento.FuncionarioProcedimentoRequest;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.dto.response.FuncionarioProcedimento.FuncionarioProcedimentoResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.services.FuncionarioProcedimentoService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario_procedimento")
@Tag(name = "Funcionario Procedimento", description = "Endpoints para gerenciar os relacionamentos entre Funcionários e Procedimentos")
public class FuncionarioProcedimentoController {

    @Autowired
    private FuncionarioProcedimentoService service;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar todos os Funcionário-Procedimento", 
               description = "Retorna uma lista de todos os relacionamentos entre Funcionários e Procedimentos",
               responses = {
                   @ApiResponse(description = "Lista encontrada com sucesso", responseCode = "200", 
                       content = @Content(array = @ArraySchema(schema = @Schema(implementation = FuncionarioProcedimentoResponse.class)))),
                   @ApiResponse(description = "Nenhum relacionamento encontrado", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<List<FuncionarioProcedimentoResponse>> findAll() {
        List<FuncionarioProcedimentoResponse> funcionariosprocedimentos = service.findAll();
        if (funcionariosprocedimentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionariosprocedimentos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar Funcionário-Procedimento por ID", 
               description = "Busca um relacionamento específico entre Funcionário e Procedimento pelo ID",
               responses = {
                   @ApiResponse(description = "Relacionamento encontrado com sucesso", responseCode = "200", 
                       content = @Content(schema = @Schema(implementation = FuncionarioProcedimentoResponse.class))),
                   @ApiResponse(description = "Relacionamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioProcedimentoResponse> findById(@PathVariable(value = "id") Long id) {
        FuncionarioProcedimentoResponse funcionarioprocedimento = service.findById(id);
        if (funcionarioprocedimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarioprocedimento);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novo Funcionário-Procedimento", 
               description = "Cria um novo relacionamento entre Funcionário e Procedimento",
               responses = {
                   @ApiResponse(description = "Relacionamento criado com sucesso", responseCode = "201", 
                       content = @Content(schema = @Schema(implementation = FuncionarioProcedimentoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioProcedimentoResponse> create(@RequestBody FuncionarioProcedimentoRequest funcionarioProcedimentoRequest, @AuthenticationPrincipal UserDetails userDetails) {
        
        // Obter o usuário autenticado
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verificar se o ID do usuário autenticado é o mesmo que o ID do usuário do funcionário no request ou se é um ADMIN
        FuncionarioResponse funcionario = funcionarioService.findById(funcionarioProcedimentoRequest.getFuncionario().getId());
        if (funcionario == null || (!authenticatedUser.getId().equals(funcionario.getUser()) && 
            !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        FuncionarioProcedimentoResponse createdFuncionarioProcedimento = service.create(funcionarioProcedimentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFuncionarioProcedimento);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar Funcionário-Procedimento", 
               description = "Atualiza um relacionamento existente entre Funcionário e Procedimento",
               responses = {
                   @ApiResponse(description = "Relacionamento atualizado com sucesso", responseCode = "200", 
                       content = @Content(schema = @Schema(implementation = FuncionarioProcedimentoResponse.class))),
                   @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content),
                   @ApiResponse(description = "Relacionamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<FuncionarioProcedimentoResponse> update(@PathVariable(value = "id") Long id, 
                                                                  @RequestBody FuncionarioProcedimentoRequest funcionarioProcedimentoRequest,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {
        // Obter o usuário autenticado
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verificar se o ID do usuário autenticado é o mesmo que o ID do usuário do funcionário no request ou se é um ADMIN
        FuncionarioResponse funcionario = funcionarioService.findById(funcionarioProcedimentoRequest.getFuncionario().getId());
        if (funcionario == null || (!authenticatedUser.getId().equals(funcionario.getUser()) && 
            !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
                                                                    
        FuncionarioProcedimentoResponse updatedFuncionarioProcedimento = service.update(id, funcionarioProcedimentoRequest);
        if (updatedFuncionarioProcedimento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFuncionarioProcedimento);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar Funcionário-Procedimento", 
               description = "Remove um relacionamento entre Funcionário e Procedimento pelo ID",
               responses = {
                   @ApiResponse(description = "Relacionamento removido com sucesso", responseCode = "204", content = @Content),
                   @ApiResponse(description = "Relacionamento não encontrado", responseCode = "404", content = @Content),
                   @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
               })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        // Obter o usuário autenticado
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Verificar se o ID do usuário autenticado é o mesmo que o ID do usuário do funcionário no request ou se é um ADMIN
        FuncionarioResponse funcionario = funcionarioService.findById(id);
        if (funcionario == null || (!authenticatedUser.getId().equals(funcionario.getUser()) && 
            !userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}