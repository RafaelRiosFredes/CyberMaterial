package com.rrios.msvc.productos.msvc_productos.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.rrios.msvc.productos.msvc_productos.dtos.ProductoDTO;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


//se crea en un puerto random
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {
    @Autowired
    TestRestTemplate restTemplate; //elemento que genera apis de trabajo

    @Test
    public void shouldReturnAllProductoWhenListIsRequired(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/productos",String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productosCount= documentContext.read("$.length()");
        assertThat(productosCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idProducto");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray names = documentContext.read("$..nombreProducto");
        assertThat(names).containsExactlyInAnyOrder("Caja","Lapiz grafito");
    }

    @Test
    public void shouldReturnAnProductoWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/productos/1",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProducto = documentContext.read("$.idProducto");//un punto cuando se llama a un objeto unico
        assertThat(idProducto).isEqualTo(1);

        String nombreProducto = documentContext.read("$.nombreProducto");
        assertThat(nombreProducto).isEqualTo("Caja");
    }

    @Test
    public void shouldReturnAnProductoWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/productos/99999",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProducto(){
        ProductoDTO productoDTO = new ProductoDTO("Lapiz grafito", 200000, "Lapiz grafito");
        Producto producto = new Producto("Lapiz grafito", 200000, "Lapiz grafito");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/productos",producto, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProducto = documentContext.read("$.idProducto");
        assertThat(idProducto).isEqualTo(3);
    }
}






