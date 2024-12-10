package com.caio.barbearia.entities;

import java.util.Collection;
import java.util.List;

import com.caio.barbearia.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Funcionario funcionario;

    public User(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.role) {
            case ADMIN:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_CLIENTE"),
                        new SimpleGrantedAuthority("ROLE_FUNCIONARIO")
                );
            case FUNCIONARIO:
                return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
            case CLIENTE:
            default:
                return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        }
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
