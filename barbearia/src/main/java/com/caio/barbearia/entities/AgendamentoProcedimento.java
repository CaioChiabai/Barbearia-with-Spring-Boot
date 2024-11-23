package com.caio.barbearia.entities;

import jakarta.persistence.*;

@Entity
public class AgendamentoProcedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario_procedimento", nullable = false)
    private Agendamento agendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario_procedimento", nullable = false)
    private FuncionarioProcedimento funcionarioProcedimento;

    @Column(nullable = false)
    private Integer quantidade;

    public AgendamentoProcedimento(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public FuncionarioProcedimento getFuncionarioProcedimento() {
        return funcionarioProcedimento;
    }

    public void setFuncionarioProcedimento(FuncionarioProcedimento funcionarioProcedimento) {
        this.funcionarioProcedimento = funcionarioProcedimento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((agendamento == null) ? 0 : agendamento.hashCode());
        result = prime * result + ((funcionarioProcedimento == null) ? 0 : funcionarioProcedimento.hashCode());
        result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AgendamentoProcedimento other = (AgendamentoProcedimento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (agendamento == null) {
            if (other.agendamento != null)
                return false;
        } else if (!agendamento.equals(other.agendamento))
            return false;
        if (funcionarioProcedimento == null) {
            if (other.funcionarioProcedimento != null)
                return false;
        } else if (!funcionarioProcedimento.equals(other.funcionarioProcedimento))
            return false;
        if (quantidade == null) {
            if (other.quantidade != null)
                return false;
        } else if (!quantidade.equals(other.quantidade))
            return false;
        return true;
    }

   
}
