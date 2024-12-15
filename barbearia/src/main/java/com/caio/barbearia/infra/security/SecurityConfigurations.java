package com.caio.barbearia.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Restringir acesso à entidade Procedimento
                        .requestMatchers(HttpMethod.GET, "/procedimento/**").hasAnyRole("ADMIN", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/procedimento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/procedimento/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/procedimento/**").hasRole("ADMIN")

                        // Restringir acesso à entidade CLIENTE
                        .requestMatchers(HttpMethod.GET, "/cliente").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cliente/{id}").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/cliente/{id}/agendamento").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/cliente/**").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/cliente/**").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/cliente/**").hasRole("ADMIN")

                        // Restringir acesso à entidade FUNCIONARIO
                        .requestMatchers(HttpMethod.GET, "/funcionario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/funcionario/{id}").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.GET, "/funcionario/{id}/agendamento").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/funcionario/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/funcionario/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/**").hasRole("ADMIN")

                        // Restringir acesso à entidade JORNADA_TRABALHO
                        .requestMatchers(HttpMethod.GET, "/jornada_trabalho/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/jornada_trabalho/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jornada_trabalho/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jornada_trabalho/**").hasRole("ADMIN")

                        // Restringir acesso à entidade FUNCIONARIO_PROCEDIMENTO
                        .requestMatchers(HttpMethod.GET, "/funcionario_procedimento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/funcionario_procedimento/{id}").hasAnyRole("ADMIN","FUNCIONARIO","CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/funcionario_procedimento/**").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/funcionario_procedimento/**").hasAnyRole("ADMIN","FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario_procedimento/**").hasAnyRole("ADMIN","FUNCIONARIO")

                        // Restringir acesso à entidade AGENDAMENTO
                        .requestMatchers(HttpMethod.GET, "/agendamento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/agendamento/{id}").hasAnyRole("ADMIN","FUNCIONARIO","CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/agendamento/**").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/agendamento/**").hasAnyRole("ADMIN","CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/agendamento/**").hasAnyRole("ADMIN","CLIENTE")

                        //Liberando acesso ao swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
