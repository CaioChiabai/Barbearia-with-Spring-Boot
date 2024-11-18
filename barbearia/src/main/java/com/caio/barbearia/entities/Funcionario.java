package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Funcionario extends Pessoa{

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @OneToOne(mappedBy = "jornada_trabalho")
    private JornadaTrabalho jornadaTrabalho;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<AgendamentoProcedimento> agendamentoProcedimentos;

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

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public JornadaTrabalho getJornadaTrabalho() {
        return jornadaTrabalho;
    }

    public void setJornadaTrabalho(JornadaTrabalho jornadaTrabalho) {
        this.jornadaTrabalho = jornadaTrabalho;
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
        if (!super.equals(o)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(cargo, that.cargo) && Objects.equals(salario, that.salario) && Objects.equals(dataContratacao, that.dataContratacao) && Objects.equals(jornadaTrabalho, that.jornadaTrabalho) && Objects.equals(agendamentoProcedimentos, that.agendamentoProcedimentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo, salario, dataContratacao, jornadaTrabalho, agendamentoProcedimentos);
    }
}
