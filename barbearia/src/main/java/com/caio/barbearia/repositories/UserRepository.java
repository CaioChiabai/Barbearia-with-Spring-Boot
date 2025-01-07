package com.caio.barbearia.repositories;

import com.caio.barbearia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
    Optional<User> findOptionalByEmail(String email);
}
