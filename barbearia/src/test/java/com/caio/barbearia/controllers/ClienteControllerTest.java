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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @Mock
    private AgendamentoService agendamentoService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void testFindAll_WhenClientesExist() {
        List<ClienteResponse> clienteResponses = List.of(new ClienteResponse());
        when(clienteService.findAll()).thenReturn(clienteResponses);

        ResponseEntity<List<ClienteResponse>> response = clienteController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteResponses, response.getBody());
    }

    @Test
    void testFindAll_WhenNoClientesExist() {
        when(clienteService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ClienteResponse>> response = clienteController.findAll();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testFindById_Success() {
        Long id = 1L;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.CLIENTE);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(id);

        when(authentication.getPrincipal()).thenReturn(user);
        when(clienteService.findClienteByUserId(user.getId())).thenReturn(clienteResponse);
        when(clienteService.findById(id)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = clienteController.findById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteResponse, response.getBody());
    }

    @Test
    void testFindById_AccessDenied() {
        Long id = 2L;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.CLIENTE);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(1L);

        when(authentication.getPrincipal()).thenReturn(user);
        when(clienteService.findClienteByUserId(user.getId())).thenReturn(clienteResponse);

        assertThrows(AccessDeniedException.class, () -> clienteController.findById(id));
    }

    @Test
    void testUpdate_Success() {
        Long id = 1L;
        ClienteRequest clienteRequest = new ClienteRequest();
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(id);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.CLIENTE);

        when(authentication.getPrincipal()).thenReturn(user);
        when(clienteService.findClienteByUserId(user.getId())).thenReturn(clienteResponse);
        when(clienteService.update(id, clienteRequest)).thenReturn(clienteResponse);

        ResponseEntity<ClienteResponse> response = clienteController.update(id, clienteRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteResponse, response.getBody());
    }

    @Test
    void testDelete_Success() {
        Long id = 1L;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.CLIENTE);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(id);

        when(authentication.getPrincipal()).thenReturn(user);
        when(clienteService.findClienteByUserId(user.getId())).thenReturn(clienteResponse);

        ResponseEntity<Void> response = clienteController.delete(id);

        verify(clienteService, times(1)).delete(id);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testListarAgendamentos_Success() {
        Long id = 1L;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.CLIENTE);

        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(id);

        List<AgendamentoResponse> agendamentoResponses = List.of(new AgendamentoResponse());

        when(authentication.getPrincipal()).thenReturn(user);
        when(clienteService.findClienteByUserId(user.getId())).thenReturn(clienteResponse);
        when(agendamentoService.findByClienteId(id)).thenReturn(agendamentoResponses);

        ResponseEntity<List<AgendamentoResponse>> response = clienteController.listarAgendamentos(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agendamentoResponses, response.getBody());
    }
}
