package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Cliente.ClienteRequest;
import com.caio.barbearia.dto.response.Cliente.ClienteResponse;
import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.mapper.ClienteMapper;
import com.caio.barbearia.repositories.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private User user;
    private ClienteRequest clienteRequest;
    private ClienteResponse clienteResponse;

    private static final Long CLIENTE_ID = 1L;
    private static final String USER_ID = "550e8400-e29b-41d4-a716-446655440000";
    private static final String NOME = "João Silva";
    private static final String EMAIL = "joao@email.com";
    private static final String CPF = "12345678900";
    private static final String SENHA = "senha123";
    private static final String TELEFONE = "11999999999";

    @BeforeEach
    void setUp() {
        // Configuração do User
        user = new User();
        user.setId(USER_ID);
        user.setLogin("joao.silva");
        user.setPassword("password");
        user.setRole(UserRole.CLIENTE);

        // Configuração do Cliente
        cliente = new Cliente();
        cliente.setId(CLIENTE_ID);
        cliente.setNome(NOME);
        cliente.setEmail(EMAIL);
        cliente.setCpf(CPF);
        cliente.setSenha(SENHA);
        cliente.setTelefone(TELEFONE);
        cliente.setUser(user);

        // Configuração do ClienteRequest
        clienteRequest = new ClienteRequest();
        clienteRequest.setNome(NOME);
        clienteRequest.setEmail(EMAIL);
        clienteRequest.setCpf(CPF);
        clienteRequest.setSenha(SENHA);
        clienteRequest.setTelefone(TELEFONE);

        // Configuração do ClienteResponse
        clienteResponse = new ClienteResponse();
        clienteResponse.setId(CLIENTE_ID);
        clienteResponse.setNome(NOME);
        clienteResponse.setEmail(EMAIL);
        clienteResponse.setCpf(CPF);
        clienteResponse.setTelefone(TELEFONE);
    }

    @Test
    void findAll_shouldReturnEmptyList_whenNoClientsExist() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.toClienteResponseList(anyList())).thenReturn(Collections.emptyList());

        List<ClienteResponse> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper).toClienteResponseList(Collections.emptyList());
    }

    @Test
    void findAll_shouldReturnClienteResponseList() {
        List<Cliente> clientes = Arrays.asList(cliente, createAnotherCliente());
        List<ClienteResponse> expectedResponses = Arrays.asList(clienteResponse, createAnotherClienteResponse());

        when(repository.findAll()).thenReturn(clientes);
        when(mapper.toClienteResponseList(clientes)).thenReturn(expectedResponses);

        List<ClienteResponse> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedResponses, result);
        verify(repository).findAll();
        verify(mapper).toClienteResponseList(clientes);
    }

    @Test
    void findById_shouldReturnClienteResponse() {
        when(repository.findById(CLIENTE_ID)).thenReturn(Optional.of(cliente));
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.findById(CLIENTE_ID);

        assertNotNull(result);
        assertEquals(CLIENTE_ID, result.getId());
        assertEquals(NOME, result.getNome());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(CPF, result.getCpf());
        assertEquals(TELEFONE, result.getTelefone());
        verify(repository).findById(CLIENTE_ID);
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void findClienteByUserId_shouldReturnClienteResponse() {
        when(repository.findByUserId(USER_ID)).thenReturn(Optional.of(cliente));
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.findClienteByUserId(USER_ID);

        assertNotNull(result);
        assertEquals(NOME, result.getNome());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(CPF, result.getCpf());
        assertEquals(TELEFONE, result.getTelefone());
        verify(repository).findByUserId(USER_ID);
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void create_shouldReturnClienteResponse() {
        when(mapper.toCliente(clienteRequest)).thenReturn(cliente);
        when(repository.save(cliente)).thenReturn(cliente);
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.create(clienteRequest);

        assertNotNull(result);
        assertEquals(NOME, result.getNome());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(CPF, result.getCpf());
        assertEquals(TELEFONE, result.getTelefone());
        verify(mapper).toCliente(clienteRequest);
        verify(repository).save(cliente);
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void update_shouldReturnUpdatedClienteResponse() {
        ClienteRequest updateRequest = new ClienteRequest();
        updateRequest.setNome("Nome Atualizado");
        updateRequest.setEmail("novo@email.com");
        updateRequest.setCpf(CPF);
        updateRequest.setSenha(SENHA);
        updateRequest.setTelefone("11988888888");

        when(repository.findById(CLIENTE_ID)).thenReturn(Optional.of(cliente));
        when(repository.save(cliente)).thenReturn(cliente);
        when(mapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = service.update(CLIENTE_ID, updateRequest);

        assertNotNull(result);
        verify(repository).findById(CLIENTE_ID);
        verify(mapper).updateClienteFromRequest(updateRequest, cliente);
        verify(repository).save(cliente);
        verify(mapper).toClienteResponse(cliente);
    }

    @Test
    void delete_shouldDeleteCliente() {
        when(repository.existsById(CLIENTE_ID)).thenReturn(true);

        assertDoesNotThrow(() -> service.delete(CLIENTE_ID));

        verify(repository).existsById(CLIENTE_ID);
        verify(repository).deleteById(CLIENTE_ID);
    }

    private Cliente createAnotherCliente() {
        User outroUser = new User();
        outroUser.setId("660e8400-e29b-41d4-a716-446655440000");
        outroUser.setLogin("maria.silva");
        outroUser.setRole(UserRole.CLIENTE);

        Cliente outro = new Cliente();
        outro.setId(2L);
        outro.setNome("Maria Silva");
        outro.setEmail("maria@email.com");
        outro.setCpf("98765432100");
        outro.setSenha("senha456");
        outro.setTelefone("11988888888");
        outro.setUser(outroUser);

        return outro;
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