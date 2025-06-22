package com.farancibia.msvc.clientes.msvc_clientes.services;

import com.farancibia.msvc.clientes.msvc_clientes.dtos.ClienteDTO;
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
                () -> new ClienteException("El Cliente con id "+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Cliente save(ClienteDTO clienteDTO) {

            Cliente cliente = new Cliente();
            cliente.setRun(clienteDTO.getRun());
            cliente.setNombres(clienteDTO.getNombres());
            cliente.setApellidos(clienteDTO.getApellidos());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setCorreo(clienteDTO.getCorreo());
            cliente.setDireccion(clienteDTO.getDireccion());


            return this.clienteRepository.save(cliente);
        }


    @Override
    public void deleteById(Long id) {
        this.clienteRepository.deleteById(id);
    }

}
