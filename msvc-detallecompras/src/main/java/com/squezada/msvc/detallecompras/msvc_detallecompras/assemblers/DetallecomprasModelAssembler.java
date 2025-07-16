package com.squezada.msvc.detallecompras.msvc_detallecompras.assemblers;

import com.squezada.msvc.detallecompras.msvc_detallecompras.controllers.DetallecomprasControllerV2;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities.Detallecompras;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DetallecomprasModelAssembler implements RepresentationModelAssembler<Detallecompras, EntityModel<Detallecompras>> {

    @Override
    public EntityModel<Detallecompras> toModel(Detallecompras entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(DetallecomprasControllerV2.class).getDetallecompras(entity.getIdDetallecompras())).withSelfRel(),
                linkTo(methodOn(DetallecomprasControllerV2.class).findByIdBoleta(entity.getIdBoleta())).withRel("detalles de boleta")
        );
    }


}

