package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.request.Agendamento.AgendamentoRequest;
import com.caio.barbearia.dto.response.Agendamento.AgendamentoResponse;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.services.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendamentoControllerTest {

    @Mock
    private AgendamentoService service;

    @InjectMocks
    private AgendamentoController controller;

    private AgendamentoRequest agendamentoRequest;
    private AgendamentoResponse agendamentoResponse;

    @BeforeEach
    void setUp() {
        // Configurando AgendamentoRequest
        agendamentoRequest = new AgendamentoRequest();
        // Configurar campos necessários do request

        // Configurando AgendamentoResponse
        agendamentoResponse = new AgendamentoResponse();
        agendamentoResponse.setId(1L);
        agendamentoResponse.setData(LocalDate.now());
        agendamentoResponse.setHoraInicio(LocalTime.of(14, 0));
        // Configurar outros campos necessários do response
    }

    @Test
    void findAll_WhenHasContent_ShouldReturnListOfAgendamentoResponse() {
        // Arrange
        List<AgendamentoResponse> expectedResponses = Arrays.asList(agendamentoResponse);
        when(service.findAll()).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<AgendamentoResponse>> response = controller.findAll();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponses, response.getBody());
        verify(service).findAll();
    }

    @Test
    void findAll_WhenEmpty_ShouldReturnNoContent() {
        // Arrange
        when(service.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<AgendamentoResponse>> response = controller.findAll();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(service).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnAgendamentoResponse() {
        // Arrange
        when(service.findById(1L)).thenReturn(agendamentoResponse);

        // Act
        ResponseEntity<AgendamentoResponse> response = controller.findById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendamentoResponse, response.getBody());
        verify(service).findById(1L);
    }

    @Test
    void findById_WhenNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(service.findById(1L)).thenThrow(ResourceNotFoundException.class);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> controller.findById(1L));
        verify(service).findById(1L);
    }

    @Test
    void create_WhenValidRequest_ShouldReturnCreatedAgendamentoResponse() {
        // Arrange
        when(service.create(any(AgendamentoRequest.class))).thenReturn(agendamentoResponse);

        // Act
        ResponseEntity<AgendamentoResponse> response = controller.create(agendamentoRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(agendamentoResponse, response.getBody());
        verify(service).create(agendamentoRequest);
    }

    @Test
    void create_WhenInvalidRequest_ShouldReturnBadRequest() {
        // Arrange
        when(service.create(any(AgendamentoRequest.class))).thenThrow(IllegalArgumentException.class);

        // Act
        ResponseEntity<AgendamentoResponse> response = controller.create(agendamentoRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(service).create(agendamentoRequest);
    }

    @Test
    void update_WhenExists_ShouldReturnUpdatedAgendamentoResponse() {
        // Arrange
        when(service.update(eq(1L), any(AgendamentoRequest.class))).thenReturn(agendamentoResponse);

        // Act
        ResponseEntity<AgendamentoResponse> response = controller.update(1L, agendamentoRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agendamentoResponse, response.getBody());
        verify(service).update(1L, agendamentoRequest);
    }

    @Test
    void update_WhenNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(service.update(eq(1L), any(AgendamentoRequest.class))).thenThrow(ResourceNotFoundException.class);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> controller.update(1L, agendamentoRequest));
        verify(service).update(1L, agendamentoRequest);
    }

    @Test
    void delete_WhenExists_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(service).delete(1L);

        // Act
        ResponseEntity<Void> response = controller.delete(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(service).delete(1L);
    }

    @Test
    void delete_WhenNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        doThrow(ResourceNotFoundException.class).when(service).delete(1L);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> controller.delete(1L));
        verify(service).delete(1L);
    }
}