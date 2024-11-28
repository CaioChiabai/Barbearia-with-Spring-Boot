package com.caio.barbearia.dto;

import java.time.LocalTime;

public interface ProcedimentoView {

    Long getId();
    String getNome();
    Double getPreco();
    LocalTime getDuracao();
}
