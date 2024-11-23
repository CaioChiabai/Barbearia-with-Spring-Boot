package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class FuncionarioProcedimento {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_procedimento", nullable = false)
    private Procedimento procedimento;

    public FuncionarioProcedimento(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioProcedimento that = (FuncionarioProcedimento) o;
        return Objects.equals(id, that.id) && Objects.equals(funcionario, that.funcionario) && Objects.equals(procedimento, that.procedimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, funcionario, procedimento);
    }
}
