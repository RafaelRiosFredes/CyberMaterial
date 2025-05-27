package com.farancibia.msvc.clientes.msvc_clientes.services;

import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}
