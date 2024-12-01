package com.caio.barbearia.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import com.caio.barbearia.enums.Status;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_funcionario_procedimento", nullable = false)
    private FuncionarioProcedimento funcionarioProcedimento;

    @Enumerated(EnumType.STRING)
    private Status status;
}
