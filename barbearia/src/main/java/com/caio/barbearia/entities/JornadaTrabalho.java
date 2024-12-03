package com.caio.barbearia.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JornadaTrabalho {

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
}
