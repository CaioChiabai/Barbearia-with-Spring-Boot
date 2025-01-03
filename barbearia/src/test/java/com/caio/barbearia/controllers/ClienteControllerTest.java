package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.dto.response.Cliente.ClienteResponse;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.services.AgendamentoService;
import com.caio.barbearia.services.ClienteService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private AgendamentoService agendamentoService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ClienteController controller;

    private ClienteRequest clienteRequest;
    private ClienteResponse clienteResponse;
    private User authenticatedUser;
    private static final Long CLIENTE_ID = 1L;
    private static final String USER_ID = "550e8400-e29b-41d4-a716-446655440000";

    @BeforeEach
    void setUp() {
        // Configuração do ClienteRequest
        clienteRequest = new ClienteRequest();
        clienteRequest.setNome("João Silva");
        clienteRequest.setEmail("joao@email.com");
        clienteRequest.setCpf("12345678900");
        clienteRequest.setSenha("senha123");
        clienteRequest.setTelefone("11999999999");

        // Configuração do ClienteResponse
        clienteResponse = new ClienteResponse();
        clienteResponse.setId(CLIENTE_ID);
        clienteResponse.setNome("João Silva");
        clienteResponse.setEmail("joao@email.com");
        clienteResponse.setCpf("12345678900");
        clienteResponse.setTelefone("11999999999");

        // Configuração do User autenticado
        authenticatedUser = new User();
        authenticatedUser.setId(USER_ID);
        authenticatedUser.setLogin("joao.silva");
        authenticatedUser.setRole(UserRole.CLIENTE);

        // Configuração do SecurityContext
        SecurityContextHolder.setContext(securityContext);
        // Removida a configuração do authentication aqui
    }

    @Test
    void findAll_shouldReturnNoContent_whenNoClientsExist() {
        when(clienteService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ClienteResponse>> response = controller.findAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(clienteService).findAll();
    }

    @Test
    void findAll_shouldReturnClientList() {
        List<ClienteResponse> expectedList = Arrays.asList(clienteResponse, createAnotherClienteResponse());
        when(clienteService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<ClienteResponse>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());
        assertEquals(2, response.getBody().size());
        verify(clienteService).findAll();
    }

    @Test
    void findById_shouldReturnClienteResponse_whenAdmin() {
        User adminUser = new User();
        adminUser.setRole(UserRole.ADMIN);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(adminUser);
        when(clienteService.findById(CLIENTE_ID)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = controller.findById(CLIENTE_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteResponse, response.getBody());
        verify(clienteService).findById(CLIENTE_ID);
    }

    @Test
    void findById_shouldThrowAccessDeniedException_whenUnauthorizedClient() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(createAnotherClienteResponse());

        assertThrows(AccessDeniedException.class, () -> controller.findById(CLIENTE_ID));
        verify(clienteService).findClienteByUserId(USER_ID);
    }

    @Test
    void create_shouldReturnCreated_whenValidRequest() {
        when(clienteService.create(clienteRequest)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = controller.create(clienteRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteResponse, response.getBody());
        verify(clienteService).create(clienteRequest);
    }

    @Test
    void create_shouldReturnBadRequest_whenInvalidData() {
        when(clienteService.create(clienteRequest)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<ClienteResponse> response = controller.create(clienteRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(clienteService).create(clienteRequest);
    }

    @Test
    void update_shouldReturnUpdatedCliente_whenAuthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(clienteResponse);
        when(clienteService.update(CLIENTE_ID, clienteRequest)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = controller.update(CLIENTE_ID, clienteRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteResponse, response.getBody());
        verify(clienteService).update(CLIENTE_ID, clienteRequest);
    }

    @Test
    void update_shouldThrowAccessDeniedException_whenUnauthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(createAnotherClienteResponse());

        assertThrows(AccessDeniedException.class, () -> controller.update(CLIENTE_ID, clienteRequest));
        verify(clienteService).findClienteByUserId(USER_ID);
    }

    @Test
    void delete_shouldReturnNoContent_whenAuthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(clienteResponse);

        ResponseEntity<Void> response = controller.delete(CLIENTE_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService).delete(CLIENTE_ID);
    }

    @Test
    void delete_shouldThrowAccessDeniedException_whenUnauthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(createAnotherClienteResponse());

        assertThrows(AccessDeniedException.class, () -> controller.delete(CLIENTE_ID));
        verify(clienteService).findClienteByUserId(USER_ID);
    }

    @Test
    void listarAgendamentos_shouldReturnAgendamentosList_whenAuthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(clienteResponse);
        when(agendamentoService.findByClienteId(CLIENTE_ID)).thenReturn(Arrays.asList(new AgendamentoResponse(), new AgendamentoResponse()));

        ResponseEntity<List<AgendamentoResponse>> response = controller.listarAgendamentos(CLIENTE_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(agendamentoService).findByClienteId(CLIENTE_ID);
    }

    @Test
    void listarAgendamentos_shouldThrowAccessDeniedException_whenUnauthorized() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(authenticatedUser);
        when(clienteService.findClienteByUserId(USER_ID)).thenReturn(createAnotherClienteResponse());

        assertThrows(AccessDeniedException.class, () -> controller.listarAgendamentos(CLIENTE_ID));
        verify(clienteService).findClienteByUserId(USER_ID);
    }

    private ClienteResponse createAnotherClienteResponse() {
        ClienteResponse outro = new ClienteResponse();
        outro.setId(2L);
        outro.setNome("Maria Silva");
        outro.setEmail("maria@email.com");
        outro.setCpf("98765432100");
        outro.setTelefone("11988888888");
        return outro;
    }
}