package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Funcionario extends Pessoa{

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @OneToOne(mappedBy = "funcionario")
    private JornadaTrabalho jornadaTrabalho;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
        result = prime * result + ((salario == null) ? 0 : salario.hashCode());
        result = prime * result + ((dataContratacao == null) ? 0 : dataContratacao.hashCode());
        result = prime * result + ((jornadaTrabalho == null) ? 0 : jornadaTrabalho.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (cargo == null) {
            if (other.cargo != null)
                return false;
        } else if (!cargo.equals(other.cargo))
            return false;
        if (salario == null) {
            if (other.salario != null)
                return false;
        } else if (!salario.equals(other.salario))
            return false;
        if (dataContratacao == null) {
            if (other.dataContratacao != null)
                return false;
        } else if (!dataContratacao.equals(other.dataContratacao))
            return false;
        if (jornadaTrabalho == null) {
            if (other.jornadaTrabalho != null)
                return false;
        } else if (!jornadaTrabalho.equals(other.jornadaTrabalho))
            return false;
        return true;
    }

}
