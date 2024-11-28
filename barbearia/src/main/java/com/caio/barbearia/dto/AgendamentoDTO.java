package com.caio.barbearia.dto;

import java.time.LocalDateTime;

import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.entities.Status;

public class AgendamentoDTO {

    private Long id;
    private LocalDateTime dataHoraInicio;
    private Cliente cliente;
    private FuncionarioProcedimento funcionarioProcedimento;
    private Status status;
    
    public AgendamentoDTO(Long id, LocalDateTime dataHoraInicio, Cliente cliente,
            FuncionarioProcedimento funcionarioProcedimento, Status status) {
        this.id = id;
        this.dataHoraInicio = dataHoraInicio;
        this.cliente = cliente;
        this.funcionarioProcedimento = funcionarioProcedimento;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }
    
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }
    
    public FuncionarioProcedimento getFuncionarioProcedimento() {
        return funcionarioProcedimento;
    }
    
    public void setFuncionarioProcedimento(FuncionarioProcedimento funcionarioProcedimento) {
        this.funcionarioProcedimento = funcionarioProcedimento;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }   
}
