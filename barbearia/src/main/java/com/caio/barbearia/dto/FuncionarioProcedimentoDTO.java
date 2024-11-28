package com.caio.barbearia.dto;

public class FuncionarioProcedimentoDTO {

    private Long id;
    private Long idFuncionario;
    private String nomeFuncionario;
    
    public FuncionarioProcedimentoDTO(Long id, Long idFuncionario, String nomeFuncionario) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }   
}
