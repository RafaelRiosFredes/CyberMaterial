package com.farancibia.msvc.clientes.msvc_clientes.controllers;


import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ClienteService clienteService;



    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> cliente = this.clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
}
