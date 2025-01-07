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

    @Column(length = 100)
    private String cargo;

    private BigDecimal salario;

    private LocalDate dataContratacao;

    public Funcionario(String nome, String cpf, User newUser) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setUser(newUser);
    }

}
