package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Agendamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    public Agendamento() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<AgendamentoProcedimento> getAgendamentoProcedimentos() {
        return agendamentoProcedimentos;
    }

    public void setAgendamentoProcedimentos(List<AgendamentoProcedimento> agendamentoProcedimentos) {
        this.agendamentoProcedimentos = agendamentoProcedimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente) && Objects.equals(dataHoraInicio, that.dataHoraInicio) && Objects.equals(status, that.status) && Objects.equals(agendamentoProcedimentos, that.agendamentoProcedimentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, dataHoraInicio, status, agendamentoProcedimentos);
    }
}
