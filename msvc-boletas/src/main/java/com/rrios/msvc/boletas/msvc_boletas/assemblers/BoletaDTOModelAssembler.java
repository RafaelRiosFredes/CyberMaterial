package com.rrios.msvc.boletas.msvc_boletas.assemblers;

import com.rrios.msvc.boletas.msvc_boletas.controllers.BoletaControllerV2;
import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BoletaDTOModelAssembler implements RepresentationModelAssembler<BoletaDTO, EntityModel<BoletaDTO>> {
    public EntityModel<BoletaDTO> toModel(BoletaDTO entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(BoletaControllerV2.class).findDTOById(entity.getIdBoletaDto())).withSelfRel(),
                linkTo(methodOn(BoletaControllerV2.class).findAll()).withRel("boletas")
        );
    }


}
