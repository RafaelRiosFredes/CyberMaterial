package com.farancibia.msvc.inventarios.msvc_inventarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            Contact contact = new Contact();
            contact.setName("Francisca Arancibia");
            contact.setEmail("fr.arancibiac@duocuc.cl");
            return new OpenAPI()
                    .info(new Info()
                            .title("API - MSVC - Inventarios")
                            .version("1.0.0")
                            .description("Este es el microservicio de Inventarios, con el puedes realizar todas la consultas"+
                                    " CRUD que necesites")
                            .contact(contact)
                            .summary("esto es una API dentro de un proyecto de MSVC")
                    );
        }
}

