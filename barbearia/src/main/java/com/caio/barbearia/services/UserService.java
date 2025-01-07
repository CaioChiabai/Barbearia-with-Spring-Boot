package com.caio.barbearia.services;

import com.caio.barbearia.dto.response.User.UserMinResponse;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.UserMapper;
import com.caio.barbearia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserMinResponse> findAll() {
        List<User> entities = repository.findAll();
        return mapper.toUserResponseList(entities);
    }

    public UserMinResponse findByEmail(String email) {
        User entity = repository.findOptionalByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado com o email: " + email));
        return mapper.toUserResponse(entity);
    }

}
