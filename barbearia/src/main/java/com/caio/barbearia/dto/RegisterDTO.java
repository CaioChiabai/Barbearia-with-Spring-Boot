package com.caio.barbearia.dto;

import com.caio.barbearia.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
