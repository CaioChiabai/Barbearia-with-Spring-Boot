package com.caio.barbearia.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.caio.barbearia.dto.RegisterDTO;
import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.Cliente.ClienteResponse;
import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.ClienteMapper;
import com.caio.barbearia.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private ClienteResponse clienteResponse;
    private RegisterDTO registerDTO;
    private User user;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Cliente Teste", "12345678901", null);
        cliente.setId(1L);

        clienteResponse = new ClienteResponse();
        clienteResponse.setId(1L);
        clienteResponse.setNome("Cliente Teste");
        clienteResponse.setCpf("12345678901");

        registerDTO = new RegisterDTO("Cliente Teste", "12345678901", UserRole.CLIENTE, "password123", "98765432100");

        user = new User();
        user.setId("user123");
    }

    @Test
    void findAll_ShouldReturnListOfClienteResponses() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(repository.findAll()).thenReturn(clientes);
        when(mapper.toClienteResponseList(clientes)).thenReturn(Arrays.asList(clienteResponse));

        List<ClienteResponse> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clienteResponse, result.get(0));
        verify(repository).findAll();
        verify(mapper).toClienteResponseList(clientes);
    }

    @Test
    void findById_ShouldReturnClienteResponse_WhenClienteExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.findById(1L);

        assertNotNull(result);
        assertEquals(clienteResponse, result);
        verify(repository).findById(1L);
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void findById_ShouldThrowException_WhenClienteDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));

        assertEquals("Cliente não encontrado com o ID: 1", exception.getMessage());
        verify(repository).findById(1L);
    }

    @Test
    void findClienteByUserId_ShouldReturnClienteResponse_WhenClienteExists() {
        when(repository.findByUserId("user123")).thenReturn(Optional.of(cliente));
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.findClienteByUserId("user123");

        assertNotNull(result);
        assertEquals(clienteResponse, result);
        verify(repository).findByUserId("user123");
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void findClienteByUserId_ShouldThrowException_WhenClienteDoesNotExist() {
        when(repository.findByUserId("user123")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.findClienteByUserId("user123"));

        assertEquals("Funcionário não encontrado para o user ID: user123", exception.getMessage());
        verify(repository).findByUserId("user123");
    }

    @Test
    void create_ShouldSaveCliente() {
        Cliente clienteToSave = new Cliente("Cliente Teste", "12345678901", user);

        service.create(registerDTO, user);

        verify(repository).save(clienteToSave);
    }

    @Test
    void update_ShouldUpdateAndReturnClienteResponse_WhenClienteExists() {
        ClienteRequest request = new ClienteRequest("Cliente Atualizado", "98765432100", "123456789");
        Cliente updatedCliente = new Cliente("Cliente Atualizado", "98765432100", null);
        updatedCliente.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        when(repository.save(cliente)).thenReturn(updatedCliente);
        when(mapper.toClienteResponse(updatedCliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.update(1L, request);

        assertNotNull(result);
        assertEquals(clienteResponse, result);
        verify(repository).findById(1L);
        verify(mapper).updateClienteFromRequest(request, cliente);
        verify(repository).save(cliente);
        verify(mapper).toClienteResponse(updatedCliente);
    }

    @Test
    void update_ShouldThrowException_WhenClienteDoesNotExist() {
        ClienteRequest request = new ClienteRequest("Cliente Atualizado", "98765432100", "123456789");

        when(repository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.update(1L, request));

        assertEquals("Cliente não encontrado com o ID: 1", exception.getMessage());
        verify(repository).findById(1L);
    }

    @Test
    void delete_ShouldDeleteCliente_WhenClienteExists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrowException_WhenClienteDoesNotExist() {
        when(repository.existsById(1L)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));

        assertEquals("Cliente não encontrado com o ID: 1", exception.getMessage());
        verify(repository).existsById(1L);
    }
}
