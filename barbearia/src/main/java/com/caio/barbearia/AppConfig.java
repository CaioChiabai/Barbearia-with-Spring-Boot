package com.caio.barbearia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI configOpenAPI(){
        return new OpenAPI().info(
            new Info().description("API's para uma barbearia")
                .version("1.0.0")
                .title("Validações Api Barbearia")
        );           
    }
}
