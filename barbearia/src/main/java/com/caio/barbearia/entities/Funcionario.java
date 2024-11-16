package com.caio.barbearia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Funcionario extends Pessoa{

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false)
    private LocalTime horaEntrada;

    @Column(nullable = false)
    private LocalTime horaSaida;

    @Column(nullable = false)
    private LocalTime horaAlmocoInicio;

    @Column(nullable = false)
    private LocalTime horaAlmocoFim;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @OneToMany(mappedBy = "funcionario")
    private List<AgendamentoProcedimento> procedimentos = new ArrayList<>();

    public Funcionario(){}

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraAlmocoInicio() {
        return horaAlmocoInicio;
    }

    public void setHoraAlmocoInicio(LocalTime horaAlmocoInicio) {
        this.horaAlmocoInicio = horaAlmocoInicio;
    }

    public LocalTime getHoraAlmocoFim() {
        return horaAlmocoFim;
    }

    public void setHoraAlmocoFim(LocalTime horaAlmocoFim) {
        this.horaAlmocoFim = horaAlmocoFim;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public List<AgendamentoProcedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<AgendamentoProcedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(cargo, that.cargo) && Objects.equals(salario, that.salario) && Objects.equals(horaEntrada, that.horaEntrada) && Objects.equals(horaSaida, that.horaSaida) && Objects.equals(horaAlmocoInicio, that.horaAlmocoInicio) && Objects.equals(horaAlmocoFim, that.horaAlmocoFim) && Objects.equals(dataContratacao, that.dataContratacao) && Objects.equals(procedimentos, that.procedimentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo, salario, horaEntrada, horaSaida, horaAlmocoInicio, horaAlmocoFim, dataContratacao, procedimentos);
    }
}
