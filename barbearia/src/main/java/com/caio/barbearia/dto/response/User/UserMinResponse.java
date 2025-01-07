package com.caio.barbearia.dto.response.User;

import com.caio.barbearia.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMinResponse {
    String id;
    String email;
    UserRole role;
}
