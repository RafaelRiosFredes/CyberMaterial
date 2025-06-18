package com.squezada.msvc.detallecompras.msvc_detallecompras.Controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.BoletaDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.DetalledecomprasDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ProductoDTO;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DetallecomprasControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    //Obtiene todos los detalles de compra
    @Test
    public void shouldReturnAllDetallecomprasWhenListIsRequired() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/detallecompras", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int detallecomprasCount = documentContext.read("$.length");
        assertThat(detallecomprasCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idDetallecompras");
        assertThat(ids).containsExactlyInAnyOrder(1, 2);


    }


    //Obtiene un detalle de compras con un ID valido
    @Test
    public void shouldReturnAnDetallecomprasWhenFindById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/detallecompras/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idDetallecompras = documentContext.read("$.idDetallecompras");//un punto cuando se llama a un objeto unico
        assertThat(idDetallecompras).isEqualTo(1);
    }

    //Buscar un ID inexistente
    @Test
    public void shouldReturnAnDetallecomprasWithUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/detallecompras/99999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);

    }


    //Crear un nuevo detalle de compra
    @Test
    @DirtiesContext
    public void shouldCreateANewDetallecompras() throws Exception {
        DetalledecomprasDTO detalledecomprasDTO = new DetalledecomprasDTO();
        {

            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(1L); // asegúrate de que exista
            productoDTO.setNombreProducto("Caja");
            productoDTO.setPrecio(20000.0);
            productoDTO.setDescripcion("Lapiz grafito");

            BoletaDTO boletaDTO = new BoletaDTO();
            boletaDTO.setIdBoleta(1L);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaBoleta = formatter.parse("2024-06-17");
            boletaDTO.setFechaBoleta(fechaBoleta);


            detalledecomprasDTO.setCantidad(2L);
            detalledecomprasDTO.setTotal(2000.0);
            detalledecomprasDTO.setProductoDTO(productoDTO);
            detalledecomprasDTO.setBoletaDTO(boletaDTO);

            // Enviar POST

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "/api/v1/detallecompras", detalledecomprasDTO, String.class
            );

            // Validar respuesta
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

            // Analizar respuesta JSON
            DocumentContext documentContext = JsonPath.parse(response.getBody());
            Number idDetallecompras = documentContext.read("$.idDetallecompras");

            assertThat(idDetallecompras).isNotNull();
            assertThat(idDetallecompras.longValue()).isGreaterThan(0);

            Long cantidad = documentContext.read("$.cantidad");
            Double total = documentContext.read("$.total");

            assertThat(cantidad).isEqualTo(2L);
            assertThat(total).isEqualTo(2000.0);
        }

    }
}

