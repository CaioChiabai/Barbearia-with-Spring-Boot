package com.caio.barbearia.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Objects;

@Entity
public class JornadaTrabalho {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    @Column(nullable = false)
    private LocalTime inicioJornada;

    @Column(nullable = false)
    private LocalTime fimJornada;

    @Column(nullable = false)
    private LocalTime inicioIntervalo;

    @Column(nullable = false)
    private LocalTime fimIntervalo;

    public JornadaTrabalho(){}

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

    public LocalTime getInicioJornada() {
        return inicioJornada;
    }

    public void setInicioJornada(LocalTime inicioJornada) {
        this.inicioJornada = inicioJornada;
    }

    public LocalTime getFimJornada() {
        return fimJornada;
    }

    public void setFimJornada(LocalTime fimJornada) {
        this.fimJornada = fimJornada;
    }

    public LocalTime getInicioIntervalo() {
        return inicioIntervalo;
    }

    public void setInicioIntervalo(LocalTime inicioIntervalo) {
        this.inicioIntervalo = inicioIntervalo;
    }

    public LocalTime getFimIntervalo() {
        return fimIntervalo;
    }

    public void setFimIntervalo(LocalTime fimIntervalo) {
        this.fimIntervalo = fimIntervalo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JornadaTrabalho that = (JornadaTrabalho) o;
        return Objects.equals(id, that.id) && Objects.equals(funcionario, that.funcionario) && Objects.equals(inicioJornada, that.inicioJornada) && Objects.equals(fimJornada, that.fimJornada) && Objects.equals(inicioIntervalo, that.inicioIntervalo) && Objects.equals(fimIntervalo, that.fimIntervalo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, funcionario, inicioJornada, fimJornada, inicioIntervalo, fimIntervalo);
    }
}
