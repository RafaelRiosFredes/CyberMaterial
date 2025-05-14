package com.farancibia.msvc.clientes.msvc_clientes.controllers;


import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@Validated
public class ClienteController {

    //Autowired de clienteService
    @

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
    List<Cliente> clientes = this.pa
    }
}
