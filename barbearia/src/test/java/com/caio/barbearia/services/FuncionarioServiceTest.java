package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.Funcionario.FuncionarioRequest;
import com.caio.barbearia.dto.response.Funcionario.FuncionarioResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.FuncionarioMapper;
import com.caio.barbearia.repositories.FuncionarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private FuncionarioMapper mapper;

    @InjectMocks
    private FuncionarioService service;

    private Funcionario funcionario;
    private User user;
    private FuncionarioRequest funcionarioRequest;
    private FuncionarioResponse funcionarioResponse;

    @BeforeEach
    void setUp() {
        // Configurando User
        user = new User("funcionario@test.com", "password123", UserRole.FUNCIONARIO);
        user.setId("user123");

        // Configurando Funcionario com campos da classe Pessoa
        funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("João Silva");
        funcionario.setSenha("senha123");
        funcionario.setCpf("12345678901");
        funcionario.setEmail("joao@test.com");

        // Campos específicos de Funcionario
        funcionario.setCargo("Barbeiro");
        funcionario.setSalario(new BigDecimal("2500.00"));
        funcionario.setDataContratacao(LocalDate.of(2023, 1, 1));
        funcionario.setUser(user);

        // Configurando FuncionarioRequest
        funcionarioRequest = new FuncionarioRequest();
        // Aqui você deve configurar todos os campos necessários do request
        // de acordo com sua implementação do DTO

        // Configurando FuncionarioResponse
        funcionarioResponse = new FuncionarioResponse();
        funcionarioResponse.setId(1L);
        // Configure outros campos do response de acordo com sua implementação do DTO
    }

    @Test
    void findAll_ShouldReturnListOfFuncionarioResponse() {
        // Arrange
        List<Funcionario> funcionarios = Arrays.asList(funcionario);
        List<FuncionarioResponse> expectedResponses = Arrays.asList(funcionarioResponse);

        when(repository.findAll()).thenReturn(funcionarios);
        when(mapper.toFuncionarioResponseList(funcionarios)).thenReturn(expectedResponses);

        // Act
        List<FuncionarioResponse> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponses.size(), result.size());
        verify(repository).findAll();
        verify(mapper).toFuncionarioResponseList(funcionarios);
    }

    @Test
    void findById_WhenFuncionarioExists_ShouldReturnFuncionarioResponse() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(mapper.toFuncionarioResponse(funcionario)).thenReturn(funcionarioResponse);

        // Act
        FuncionarioResponse result = service.findById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(funcionarioResponse.getId(), result.getId());
        verify(repository).findById(1L);
        verify(mapper).toFuncionarioResponse(funcionario);
    }

    @Test
    void findById_WhenFuncionarioNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
        verify(repository).findById(1L);
        verify(mapper, never()).toFuncionarioResponse(any());
    }

    @Test
    void findFuncionarioByUserId_WhenExists_ShouldReturnFuncionarioResponse() {
        // Arrange
        when(repository.findByUserId("user123")).thenReturn(Optional.of(funcionario));
        when(mapper.toFuncionarioResponse(funcionario)).thenReturn(funcionarioResponse);

        // Act
        FuncionarioResponse result = service.findFuncionarioByUserId("user123");

        // Assert
        assertNotNull(result);
        verify(repository).findByUserId("user123");
        verify(mapper).toFuncionarioResponse(funcionario);
    }

    @Test
    void findFuncionarioByUserId_WhenNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.findByUserId("user123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                service.findFuncionarioByUserId("user123"));
        verify(repository).findByUserId("user123");
    }

    @Test
    void create_ShouldReturnCreatedFuncionarioResponse() {
        // Arrange
        when(mapper.toFuncionario(funcionarioRequest)).thenReturn(funcionario);
        when(repository.save(funcionario)).thenReturn(funcionario);
        when(mapper.toFuncionarioResponse(funcionario)).thenReturn(funcionarioResponse);

        // Act
        FuncionarioResponse result = service.create(funcionarioRequest);

        // Assert
        assertNotNull(result);
        verify(mapper).toFuncionario(funcionarioRequest);
        verify(repository).save(funcionario);
        verify(mapper).toFuncionarioResponse(funcionario);
    }

    @Test
    void update_WhenFuncionarioExists_ShouldReturnUpdatedFuncionarioResponse() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(repository.save(funcionario)).thenReturn(funcionario);
        when(mapper.toFuncionarioResponse(funcionario)).thenReturn(funcionarioResponse);

        // Act
        FuncionarioResponse result = service.update(1L, funcionarioRequest);

        // Assert
        assertNotNull(result);
        verify(repository).findById(1L);
        verify(mapper).updateFuncionarioFromRequest(funcionarioRequest, funcionario);
        verify(repository).save(funcionario);
        verify(mapper).toFuncionarioResponse(funcionario);
    }

    @Test
    void update_WhenFuncionarioNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () ->
                service.update(1L, funcionarioRequest));
        verify(repository).findById(1L);
        verify(repository, never()).save(any());
    }

    @Test
    void delete_WhenFuncionarioExists_ShouldDeleteSuccessfully() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(true);

        // Act
        service.delete(1L);

        // Assert
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void delete_WhenFuncionarioNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(repository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
        verify(repository).existsById(1L);
        verify(repository, never()).deleteById(any());
    }
}