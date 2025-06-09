package com.squezada.msvc.sucursales.msvc_sucursales.controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import feign.Response;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SucursalesControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllSucursalesWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1/sucursales", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int sucursalesCount = documentContext.read("$.length()");
        assertThat(sucursalesCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idSucursal");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray horario = documentContext.read("$..horario");
        assertThat(horario).containsExactlyInOrder("9:00-19:00");

        JSONArray direccion = documentContext.read("$..Direccion");
        assertThat(direccion).containsExactlyInOrder("Pedro Aguirre 123");


    }

    @Test
    public void shouldReturnAnSucursalesWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1/sucursales/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idSucursal = documentContext.read("$..idSucursal");
        assertThat(idSucursal).isEqualTo(1);

        String horario = documentContext.read("$.horario");
        assertThat(horario).isEqualTo("9:00-19:00");

        String direccion = documentContext.read("$.direccion");
        assertThat(direccion).isEqualTo("Pedro Aguirre 123");

    }

    @Test
    public void shouldReturnAnSucursalesWithUnknownId(){
        ResponseEntity<String>response =restTemplate.getForEntity("api/v1/sucursales/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse()
    }
}
