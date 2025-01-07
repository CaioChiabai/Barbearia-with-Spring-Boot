package com.caio.barbearia.mapper;

import com.caio.barbearia.dto.request.User.UserMinRequest;
import com.caio.barbearia.dto.response.User.UserMinResponse;
import com.caio.barbearia.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toUser(UserMinRequest request){ return mapper.map(request, User.class);}

    public UserMinResponse toUserResponse(User user){
        return mapper.map(user, UserMinResponse.class);
    }

    public List<UserMinResponse> toUserResponseList(List<User> users){
        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }
}
