package com.farancibia.msvc.clientes.msvc_clientes.assemblers;

import com.farancibia.msvc.clientes.msvc_clientes.controllers.ClienteControllerV2;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ClienteControllerV2.class).findById(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).findALL()).withRel("clientes")
        );
    }
}
