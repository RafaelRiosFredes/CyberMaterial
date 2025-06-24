package com.farancibia.msvc.inventarios.msvc_inventarios.assemblers;

import com.farancibia.msvc.inventarios.msvc_inventarios.controllers.InventarioControllerV2;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<InventarioDTO, EntityModel<InventarioDTO>> {
    @Override
    public EntityModel<InventarioDTO> toModel(InventarioDTO inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withRel("self"));
    }

}

