package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.entities.*;
import com.caio.barbearia.enums.Status;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.AgendamentoMapper;
import com.caio.barbearia.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private AgendamentoMapper mapper;

    @InjectMocks
    private AgendamentoService service;

    private Agendamento agendamento;
    private AgendamentoRequest agendamentoRequest;
    private AgendamentoResponse agendamentoResponse;
    private Cliente cliente;
    private Funcionario funcionario;
    private Procedimento procedimento;
    private FuncionarioProcedimento funcionarioProcedimento;

    @BeforeEach
    void setUp() {
        // Configurando Cliente
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Teste");

        // Configurando Funcionario e Procedimento
        funcionario = new Funcionario();
        funcionario.setId(2L);
        funcionario.setNome("Funcionário Teste");

        procedimento = new Procedimento();
        procedimento.setId(1L);
        procedimento.setNome("Corte de Cabelo");

        // Configurando FuncionarioProcedimento
        funcionarioProcedimento = new FuncionarioProcedimento();
        funcionarioProcedimento.setId(1L);
        funcionarioProcedimento.setFuncionario(funcionario);
        funcionarioProcedimento.setProcedimento(procedimento);

        // Configurando Agendamento com o status correto EM_ABERTO
        agendamento = new Agendamento();
        agendamento.setId(1L);
        agendamento.setCliente(cliente);
        agendamento.setFuncionarioProcedimento(funcionarioProcedimento);
        agendamento.setData(LocalDate.now());
        agendamento.setHoraInicio(LocalTime.of(14, 0));
        agendamento.setStatus(Status.EM_ABERTO); // Atualizando para usar o status correto

        // Configurando AgendamentoRequest
        agendamentoRequest = new AgendamentoRequest();
        // Configurar campos necessários do request

        // Configurando AgendamentoResponse
        agendamentoResponse = new AgendamentoResponse();
        agendamentoResponse.setId(1L);
        // Configurar outros campos necessários do response
    }

    @Test
    void findAll_ShouldReturnListOfAgendamentoResponse() {
        // Arrange
        List<Agendamento> agendamentos = Arrays.asList(agendamento);
        List<AgendamentoResponse> expectedResponses = Arrays.asList(agendamentoResponse);

        when(repository.findAll()).thenReturn(agendamentos);
        when(mapper.toAgendamentoResponseList(agendamentos)).thenReturn(expectedResponses);

        // Act
        List<AgendamentoResponse> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponses.size(), result.size());
        verify(repository).findAll();
        verify(mapper).toAgendamentoResponseList(agendamentos);
    }

    @Test
    void findById_WhenAgendamentoExists_ShouldReturnAgendamentoResponse() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(agendamento));
        when(mapper.toAgendamentoResponse(agendamento)).thenReturn(agendamentoResponse);

        // Act
        AgendamentoResponse result = service.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(agendamentoResponse.getId(), result.getId());
        verify(repository).findById(1L);
        verify(mapper).toAgendamentoResponse(agendamento);
    }

    @Test
    void findById_WhenAgendamentoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
        verify(repository).findById(1L);
        verify(mapper, never()).toAgendamentoResponse(any());
    }

    @Test
    void create_ShouldReturnCreatedAgendamentoResponse() {
        // Arrange
        when(mapper.toAgendamento(agendamentoRequest)).thenReturn(agendamento);
        when(repository.save(agendamento)).thenReturn(agendamento);
        when(mapper.toAgendamentoResponse(agendamento)).thenReturn(agendamentoResponse);

        // Act
        AgendamentoResponse result = service.create(agendamentoRequest);

        // Assert
        assertNotNull(result);
        verify(mapper).toAgendamento(agendamentoRequest);
        verify(repository).save(agendamento);
        verify(mapper).toAgendamentoResponse(agendamento);
    }

    @Test
    void update_WhenAgendamentoExists_ShouldReturnUpdatedAgendamentoResponse() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(agendamento));
        when(repository.save(agendamento)).thenReturn(agendamento);
        when(mapper.toAgendamentoResponse(agendamento)).thenReturn(agendamentoResponse);

        // Act
        AgendamentoResponse result = service.update(1L, agendamentoRequest);

        // Assert
        assertNotNull(result);
        verify(repository).findById(1L);
        verify(mapper).updateAgendamentoFromRequest(agendamentoRequest, agendamento);
        verify(repository).save(agendamento);
        verify(mapper).toAgendamentoResponse(agendamento);
    }

    @Test
    void update_WhenAgendamentoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                service.update(1L, agendamentoRequest));
        verify(repository).findById(1L);
        verify(repository, never()).save(any());
    }

    @Test
    void delete_WhenAgendamentoExists_ShouldDeleteSuccessfully() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(true);

        // Act
        service.delete(1L);

        // Assert
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void delete_WhenAgendamentoNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
        verify(repository).existsById(1L);
        verify(repository, never()).deleteById(any());
    }

    @Test
    void findByFuncionarioId_ShouldReturnListOfAgendamentoResponse() {
        // Arrange
        List<Agendamento> agendamentos = Arrays.asList(agendamento);
        List<AgendamentoResponse> expectedResponses = Arrays.asList(agendamentoResponse);

        when(repository.findByFuncionarioId(2L)).thenReturn(agendamentos);
        when(mapper.toAgendamentoResponseList(agendamentos)).thenReturn(expectedResponses);

        // Act
        List<AgendamentoResponse> result = service.findByFuncionarioId(2L);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponses.size(), result.size());
        verify(repository).findByFuncionarioId(2L);
        verify(mapper).toAgendamentoResponseList(agendamentos);
    }

    @Test
    void findByClienteId_ShouldReturnListOfAgendamentoResponse() {
        // Arrange
        List<Agendamento> agendamentos = Arrays.asList(agendamento);
        List<AgendamentoResponse> expectedResponses = Arrays.asList(agendamentoResponse);

        when(repository.findByClienteId(1L)).thenReturn(agendamentos);
        when(mapper.toAgendamentoResponseList(agendamentos)).thenReturn(expectedResponses);

        // Act
        List<AgendamentoResponse> result = service.findByClienteId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponses.size(), result.size());
        verify(repository).findByClienteId(1L);
        verify(mapper).toAgendamentoResponseList(agendamentos);
    }
}