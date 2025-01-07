package com.caio.barbearia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cliente extends Pessoa{

    @Column(length = 30)
    private String telefone;

    public Cliente(String nome, String cpf, User newUser) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setUser(newUser);
    }
}
