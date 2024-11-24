package com.caio.barbearia.dto;

import java.time.LocalDateTime;

public class AgendamentoProcedimentoDTO {

    private Long idAgendamento;
    private LocalDateTime dataAgendamento;
    private String nomeProcedimento;

    public AgendamentoProcedimentoDTO(Long idAgendamento, LocalDateTime dataAgendamento, String nomeProcedimento) {
        this.idAgendamento = idAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.nomeProcedimento = nomeProcedimento;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
