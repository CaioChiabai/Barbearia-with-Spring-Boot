package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoMinRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.dto.response.Cliente.ClienteMinResponse;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioMinResponse;
import com.caio.barbearia.dto.response.FuncionarioProcedimento.FuncionarioProcedimentoResponse;
import com.caio.barbearia.entities.Procedimento;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.Status;
import com.caio.barbearia.services.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AgendamentoControllerTest {

    @InjectMocks
    private AgendamentoController agendamentoController;

    @Mock
    private AgendamentoService agendamentoService;

    private AgendamentoResponse mockAgendamentoResponse() {
        ClienteMinResponse cliente = new ClienteMinResponse(1L, "Cliente Teste", "2799376412");
        FuncionarioProcedimentoResponse funcionarioProcedimento = new FuncionarioProcedimentoResponse(1L, new FuncionarioMinResponse(), new Procedimento());
        return new AgendamentoResponse(
                1L,
                cliente,
                LocalTime.of(10, 0),
                LocalDate.of(2025, 1, 20),
                funcionarioProcedimento,
                Status.EM_ABERTO
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_ShouldReturnListOfAgendamentos() {
        List<AgendamentoResponse> agendamentos = new ArrayList<>();
        agendamentos.add(mockAgendamentoResponse());
        when(agendamentoService.findAll()).thenReturn(agendamentos);

        ResponseEntity<List<AgendamentoResponse>> response = agendamentoController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agendamentos, response.getBody());
        verify(agendamentoService, times(1)).findAll();
    }

    @Test
    void testFindAll_ShouldReturnNoContentWhenEmpty() {
        when(agendamentoService.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<AgendamentoResponse>> response = agendamentoController.findAll();

        assertEquals(204, response.getStatusCodeValue());
        verify(agendamentoService, times(1)).findAll();
    }

    @Test
    void testFindById_ShouldReturnAgendamento() {
        AgendamentoResponse agendamento = mockAgendamentoResponse();
        when(agendamentoService.findById(1L)).thenReturn(agendamento);

        ResponseEntity<AgendamentoResponse> response = agendamentoController.findById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agendamento, response.getBody());
        verify(agendamentoService, times(1)).findById(1L);
    }

    @Test
    void testCreate_ShouldReturnCreatedAgendamento() {
        AgendamentoMinRequest request = new AgendamentoMinRequest(1L, LocalTime.of(10, 0), LocalDate.of(2025, 1, 20), 1L, Status.EM_ABERTO);
        AgendamentoResponse responseObj = mockAgendamentoResponse();
        when(agendamentoService.create(request)).thenReturn(responseObj);

        ResponseEntity<AgendamentoResponse> response = agendamentoController.create(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(responseObj, response.getBody());
        verify(agendamentoService, times(1)).create(request);
    }

    @Test
    void testUpdate_ShouldReturnUpdatedAgendamento() {
        AgendamentoMinRequest request = new AgendamentoMinRequest(1L, LocalTime.of(15, 0), LocalDate.of(2025, 1, 21), 1L, Status.EM_ABERTO);
        AgendamentoResponse responseObj = mockAgendamentoResponse();
        responseObj.setHoraInicio(LocalTime.of(15, 0));
        responseObj.setData(LocalDate.of(2025, 1, 21));
        responseObj.setStatus(Status.FINALIZADO);

        when(agendamentoService.update(1L, request)).thenReturn(responseObj);

        ResponseEntity<AgendamentoResponse> response = agendamentoController.update(1L, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseObj, response.getBody());
        verify(agendamentoService, times(1)).update(1L, request);
    }

    @Test
    void testDelete_ShouldReturnNoContent() {
        doNothing().when(agendamentoService).delete(1L);

        ResponseEntity<Void> response = agendamentoController.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(agendamentoService, times(1)).delete(1L);
    }
}
