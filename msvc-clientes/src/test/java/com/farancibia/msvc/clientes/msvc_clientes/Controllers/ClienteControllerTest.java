package com.farancibia.msvc.clientes.msvc_clientes.Controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllClienteWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api1/v1/clientes", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int clientescount =documentContext.read("$.length()");
        assertThat(clientescount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idCliente");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray names = documentContext.read("$..nombreCompleto");
        assertThat(names).containsExactlyInAnyOrder("Francisca Carolina", "Arancibia Chaparro");
    }

    @Test
    public void shouldReturnAnClienteWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1/clientes/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idCliente = documentContext.read("$id.Cliente");
        assertThat(idCliente).isEqualTo(1);

        String nombreCompleto = documentContext.read(("$.nombreCompleto"));
        assertThat(nombreCompleto).isEqualTo("Francisca Arancibia");
    }

    @Test
    public void shouldReturnAnClienteWithUnknownId(){
        ResponseEntity<String>response = restTemplate.getForEntity("api/v1/aclientes/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND;
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read(("$.status"));
        assertThat(status).isEqualTo(404);
    }


}
