package com.caio.barbearia.dto.response;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedimentoResponse {

    private Long id;
    private String nome;
    private Double preco;
    private LocalTime duracao;
}
