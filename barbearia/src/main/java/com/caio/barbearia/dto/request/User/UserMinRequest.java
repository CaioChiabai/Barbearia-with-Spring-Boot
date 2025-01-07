package com.caio.barbearia.dto.request.User;

import com.caio.barbearia.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMinRequest {
    String id;
    String email;
}
