package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.services.AgendamentoService;
import com.caio.barbearia.services.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioControllerTest {

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private AgendamentoService agendamentoService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private FuncionarioController controller;

    private FuncionarioResponse funcionarioResponse;
    private FuncionarioRequest funcionarioRequest;
    private User adminUser;
    private User funcionarioUser;

    @BeforeEach
    void setUp() {
        // Configurando response
        funcionarioResponse = new FuncionarioResponse();
        funcionarioResponse.setId(1L);
        funcionarioResponse.setNome("João Silva");
        funcionarioResponse.setEmail("joao@test.com");

        // Configurando request
        funcionarioRequest = new FuncionarioRequest();
        // Configure os campos necessários do request

        // Configurando usuários para testes de autorização
        adminUser = new User("admin@test.com", "password", UserRole.ADMIN);
        adminUser.setId("admin123");

        funcionarioUser = new User("func@test.com", "password", UserRole.FUNCIONARIO);
        funcionarioUser.setId("func123");

        // Configurando SecurityContext
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void findAll_WhenHasFuncionarios_ShouldReturnList() {
        // Arrange
        List<FuncionarioResponse> funcionarios = Arrays.asList(funcionarioResponse);
        when(funcionarioService.findAll()).thenReturn(funcionarios);

        // Act
        ResponseEntity<List<FuncionarioResponse>> response = controller.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarios, response.getBody());
        verify(funcionarioService).findAll();
    }

    @Test
    void findAll_WhenNoFuncionarios_ShouldReturnNoContent() {
        // Arrange
        when(funcionarioService.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<FuncionarioResponse>> response = controller.findAll();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(funcionarioService).findAll();
    }

    @Test
    void findById_WhenAdmin_ShouldReturnFuncionario() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(adminUser);
        when(funcionarioService.findById(1L)).thenReturn(funcionarioResponse);

        // Act
        ResponseEntity<FuncionarioResponse> response = controller.findById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarioResponse, response.getBody());
        verify(funcionarioService).findById(1L);
    }

    @Test
    void findById_WhenFuncionarioAccessingOwnData_ShouldReturnFuncionario() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(funcionarioUser);
        when(funcionarioService.findFuncionarioByUserId("func123")).thenReturn(funcionarioResponse);
        when(funcionarioService.findById(1L)).thenReturn(funcionarioResponse);

        // Act
        ResponseEntity<FuncionarioResponse> response = controller.findById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarioResponse, response.getBody());
    }

    @Test
    void findById_WhenFuncionarioAccessingOthersData_ShouldThrowAccessDeniedException() {
        // Arrange
        FuncionarioResponse otherFuncionario = new FuncionarioResponse();
        otherFuncionario.setId(2L);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(funcionarioUser);
        when(funcionarioService.findFuncionarioByUserId("func123")).thenReturn(funcionarioResponse);

        // Act & Assert
        assertThrows(AccessDeniedException.class, () -> controller.findById(2L));
    }

    @Test
    void create_WhenValidRequest_ShouldReturnCreatedFuncionario() {
        // Arrange
        when(funcionarioService.create(funcionarioRequest)).thenReturn(funcionarioResponse);

        // Act
        ResponseEntity<FuncionarioResponse> response = controller.create(funcionarioRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(funcionarioResponse, response.getBody());
        verify(funcionarioService).create(funcionarioRequest);
    }

    @Test
    void create_WhenInvalidRequest_ShouldReturnBadRequest() {
        // Arrange
        when(funcionarioService.create(funcionarioRequest)).thenThrow(IllegalArgumentException.class);

        // Act
        ResponseEntity<FuncionarioResponse> response = controller.create(funcionarioRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void update_WhenValidRequest_ShouldReturnUpdatedFuncionario() {
        // Arrange
        when(funcionarioService.update(1L, funcionarioRequest)).thenReturn(funcionarioResponse);

        // Act
        ResponseEntity<FuncionarioResponse> response = controller.update(1L, funcionarioRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarioResponse, response.getBody());
        verify(funcionarioService).update(1L, funcionarioRequest);
    }

    @Test
    void update_WhenFuncionarioNotFound_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(funcionarioService.update(1L, funcionarioRequest)).thenThrow(ResourceNotFoundException.class);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> controller.update(1L, funcionarioRequest));
    }

    @Test
    void delete_WhenFuncionarioExists_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(funcionarioService).delete(1L);

        // Act
        ResponseEntity<Void> response = controller.delete(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(funcionarioService).delete(1L);
    }

    @Test
    void delete_WhenFuncionarioNotFound_ShouldThrowResourceNotFoundException() {
        // Arrange
        doThrow(ResourceNotFoundException.class).when(funcionarioService).delete(1L);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> controller.delete(1L));
    }

    @Test
    void listarAgendamentos_WhenAdmin_ShouldReturnAgendamentosList() {
        // Arrange
        List<AgendamentoResponse> agendamentos = Arrays.asList(new AgendamentoResponse());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(adminUser);
        when(agendamentoService.findByFuncionarioId(1L)).thenReturn(agendamentos);

        // Act
        ResponseEntity<List<AgendamentoResponse>> response = controller.listarAgendamentos(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendamentos, response.getBody());
        verify(agendamentoService).findByFuncionarioId(1L);
    }

    @Test
    void listarAgendamentos_WhenFuncionarioAccessingOwnData_ShouldReturnAgendamentosList() {
        // Arrange
        List<AgendamentoResponse> agendamentos = Arrays.asList(new AgendamentoResponse());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(funcionarioUser);
        when(funcionarioService.findFuncionarioByUserId("func123")).thenReturn(funcionarioResponse);
        when(agendamentoService.findByFuncionarioId(1L)).thenReturn(agendamentos);

        // Act
        ResponseEntity<List<AgendamentoResponse>> response = controller.listarAgendamentos(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendamentos, response.getBody());
    }

    @Test
    void listarAgendamentos_WhenFuncionarioAccessingOthersData_ShouldThrowAccessDeniedException() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(funcionarioUser);
        when(funcionarioService.findFuncionarioByUserId("func123")).thenReturn(funcionarioResponse);

        // Act & Assert
        assertThrows(AccessDeniedException.class, () -> controller.listarAgendamentos(2L));
    }
}