package com.caio.barbearia.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Funcionario extends Pessoa{

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
