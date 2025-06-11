package com.squezada.msvc.sucursales.msvc_sucursales.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        Contact contact = new Contact();
        contact.setName("Savka Quezada");
        contact.setEmail("s.quezada@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Sucursales")
                        .version("1.0.0")
                        .description("Este es el microservicio de Sucursales, con el puedes realizar todas las consultas CRUD que necesites")
                        .contact(contact)
                        .termsOfService("No colapsar la API")
                );
    }
}
