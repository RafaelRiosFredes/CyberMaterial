package com.rrios.msvc.boletas.msvc_boletas.config;

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
        contact.setName("Rafael Rios");
        contact.setEmail("raf.rios@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Boletas")
                        .version("1.0.0")
                        .description("Este es el microservicio de Boletas, con el puedes realizar todas las consultas CRUD que necesites")
                        .contact(contact)
                        .termsOfService("No colapsar la API")
                );
    }
}
