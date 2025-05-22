package com.farancibia.msvc.clientes.msvc_clientes.services;

import com.farancibia.msvc.clientes.msvc_clientes.exceptions.ClienteException;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll(){return this.clienteRepository.findAll();}

    @Override
    public Cliente findById(Long id){
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El Cliente con is "+id+" no se encunetra en la base de datos")
        );
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }


}
