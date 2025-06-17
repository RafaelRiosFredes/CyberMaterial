package com.squezada.msvc.detallecompras.msvc_detallecompras.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DetallecomprasControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

}
