package com.caio.barbearia.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caio.barbearia.services.HorarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horarios")
@Tag(name = "Horários", description = "Endpoints para gestão de horários")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @Operation(summary = "Listar horários disponíveis", 
               description = "Retorna uma lista de horários disponíveis de um funcionário para um dia específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de horários disponíveis retornada com sucesso",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "400", description = "Erro nos parâmetros fornecidos",
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                     content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/disponiveis")
    public ResponseEntity<List<String>> listarHorariosDisponiveis(
            @RequestParam @Parameter(description = "ID do funcionário", example = "1") Long idFuncionario,
            @RequestParam @Parameter(description = "Data no formato YYYY-MM-DD", example = "2024-12-05") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        try {
            List<String> horariosDisponiveis = horarioService.getHorariosDisponiveis(idFuncionario, data);
            return ResponseEntity.ok(horariosDisponiveis);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }
}