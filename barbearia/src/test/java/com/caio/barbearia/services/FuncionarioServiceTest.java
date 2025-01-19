package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.FuncionarioMapper;
import com.caio.barbearia.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private FuncionarioMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnListOfFuncionarios() {
        List<Funcionario> mockFuncionarios = List.of(new Funcionario());
        List<FuncionarioResponse> mockResponses = List.of(new FuncionarioResponse());

        when(repository.findAll()).thenReturn(mockFuncionarios);
        when(mapper.toFuncionarioResponseList(mockFuncionarios)).thenReturn(mockResponses);

        List<FuncionarioResponse> result = funcionarioService.findAll();

        assertEquals(mockResponses, result);
        verify(repository).findAll();
        verify(mapper).toFuncionarioResponseList(mockFuncionarios);
    }

    @Test
    void findById_WhenFound_ShouldReturnFuncionarioResponse() {
        Long id = 1L;
        Funcionario mockFuncionario = new Funcionario();
        FuncionarioResponse mockResponse = new FuncionarioResponse();

        when(repository.findById(id)).thenReturn(Optional.of(mockFuncionario));
        when(mapper.toFuncionarioResponse(mockFuncionario)).thenReturn(mockResponse);

        FuncionarioResponse result = funcionarioService.findById(id);

        assertEquals(mockResponse, result);
        verify(repository).findById(id);
        verify(mapper).toFuncionarioResponse(mockFuncionario);
    }

    @Test
    void findById_WhenNotFound_ShouldThrowException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> funcionarioService.findById(id));
        verify(repository).findById(id);
    }

    @Test
    void delete_WhenExists_ShouldDeleteFuncionario() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        funcionarioService.delete(id);

        verify(repository).deleteById(id);
    }

    @Test
    void delete_WhenNotExists_ShouldThrowException() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> funcionarioService.delete(id));
        verify(repository).existsById(id);
    }
}
