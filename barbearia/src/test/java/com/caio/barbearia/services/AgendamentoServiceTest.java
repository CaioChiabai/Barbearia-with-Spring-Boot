// Testes para AgendamentoService
package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoMinRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.entities.*;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.AgendamentoMapper;
import com.caio.barbearia.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FuncionarioProcedimentoRepository funcionarioProcedimentoRepository;

    @Mock
    private AgendamentoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfAgendamentos() {
        List<Agendamento> mockAgendamentos = List.of(new Agendamento());
        List<AgendamentoResponse> mockResponses = List.of(new AgendamentoResponse());

        when(repository.findAll()).thenReturn(mockAgendamentos);
        when(mapper.toAgendamentoResponseList(mockAgendamentos)).thenReturn(mockResponses);

        List<AgendamentoResponse> result = agendamentoService.findAll();

        assertEquals(mockResponses, result);
        verify(repository).findAll();
        verify(mapper).toAgendamentoResponseList(mockAgendamentos);
    }

    @Test
    void findById_WhenFound_ShouldReturnAgendamentoResponse() {
        Long id = 1L;
        Agendamento mockAgendamento = new Agendamento();
        AgendamentoResponse mockResponse = new AgendamentoResponse();

        when(repository.findById(id)).thenReturn(Optional.of(mockAgendamento));
        when(mapper.toAgendamentoResponse(mockAgendamento)).thenReturn(mockResponse);

        AgendamentoResponse result = agendamentoService.findById(id);

        assertEquals(mockResponse, result);
        verify(repository).findById(id);
        verify(mapper).toAgendamentoResponse(mockAgendamento);
    }

    @Test
    void findById_WhenNotFound_ShouldThrowException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> agendamentoService.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void create_ShouldSaveAgendamento() {
        AgendamentoMinRequest request = new AgendamentoMinRequest();
        Cliente cliente = new Cliente();
        FuncionarioProcedimento procedimento = new FuncionarioProcedimento();
        Agendamento agendamento = new Agendamento();
        AgendamentoResponse response = new AgendamentoResponse();

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(funcionarioProcedimentoRepository.findById(anyLong())).thenReturn(Optional.of(procedimento));
        when(mapper.toAgendamento(any())).thenReturn(agendamento);
        when(repository.save(agendamento)).thenReturn(agendamento);
        when(mapper.toAgendamentoResponse(agendamento)).thenReturn(response);

        AgendamentoResponse result = agendamentoService.create(request);

        assertEquals(response, result);
        verify(repository).save(agendamento);
    }

    @Test
    void delete_WhenExists_ShouldDeleteAgendamento() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        agendamentoService.delete(id);

        verify(repository).deleteById(id);
    }

    @Test
    void delete_WhenNotExists_ShouldThrowException() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> agendamentoService.delete(id));
        verify(repository).existsById(id);
    }
}