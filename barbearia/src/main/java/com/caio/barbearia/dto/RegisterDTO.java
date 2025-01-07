package com.caio.barbearia.dto;

import com.caio.barbearia.enums.UserRole;

public record RegisterDTO(
        String email,
        String password,
        UserRole role,
        String nome,
        String cpf
) { }
