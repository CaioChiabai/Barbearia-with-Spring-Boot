package com.caio.barbearia.controllers;

import com.caio.barbearia.dto.AuthenticationDTO;
import com.caio.barbearia.dto.LoginResponseDTO;
import com.caio.barbearia.dto.RegisterDTO;
import com.caio.barbearia.entities.Cliente;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.User;
import com.caio.barbearia.enums.UserRole;
import com.caio.barbearia.infra.security.TokenService;
import com.caio.barbearia.repositories.ClienteRepository;
import com.caio.barbearia.repositories.FuncionarioRepository;
import com.caio.barbearia.repositories.UserRepository;

import com.caio.barbearia.services.ClienteService;
import com.caio.barbearia.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication", description = "Endpoints para autenticação e registro de usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Autentica um usuário e retorna um token de acesso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful login"),
        @ApiResponse(responseCode = "400", description = "Invalid login credentials")
    })
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usarnamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usarnamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Registra um novo usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful registration"),
        @ApiResponse(responseCode = "400", description = "User already exists")
    })
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null){ return ResponseEntity.badRequest().build(); }

        //Cria o usuario
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());
        this.repository.save(newUser);

        //Cria o cliente ou funcionario associado ao usuario
        if(data.role() == UserRole.CLIENTE) {
            clienteService.create(data);
        }
        if(data.role() == UserRole.FUNCIONARIO){
            funcionarioService.create(data);
        }

        return ResponseEntity.ok().build();
    }
}
