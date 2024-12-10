package com.caio.barbearia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cliente extends Pessoa{

    @Column(nullable = false, length = 30)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
