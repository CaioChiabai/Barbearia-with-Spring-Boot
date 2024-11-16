package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Agendamento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muitos agendamentos podem ser feitos por um cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Define a chave estrangeira para Cliente
    private Cliente cliente;

    @OneToMany(mappedBy = "agendamento") //mappedBy usado pois e uma relacão bidirecional
    private List<AgendamentoProcedimento> procedimentos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(nullable = false)
    private LocalDateTime dataHoraFim;

    @Column(nullable = false)
    private String status;

    public Agendamento() {}

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<AgendamentoProcedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<AgendamentoProcedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente) && Objects.equals(procedimentos, that.procedimentos) && Objects.equals(dataHoraInicio, that.dataHoraInicio) && Objects.equals(dataHoraFim, that.dataHoraFim) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, procedimentos, dataHoraInicio, dataHoraFim, status);
    }
}
