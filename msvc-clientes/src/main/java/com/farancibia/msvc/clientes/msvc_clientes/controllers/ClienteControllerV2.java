package com.farancibia.msvc.clientes.msvc_clientes.controllers;

import com.farancibia.msvc.clientes.msvc_clientes.assemblers.ClienteModelAssembler;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/cliente")
@Validated
@Tag(name = "Clientes V2", description = "Operaciones CRUD de clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;


    @GetMapping
    @Operation(summary = "Obtiene todos los clientes", description = "Devuelve un list de Clientes en el body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_FORMS_JSON_VALUE,
                            schema  = @Schema(implementation = Cliente.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> findALL(){
        List<EntityModel<Cliente>> entityModels = this.clienteService.findAll()
                .stream()
                .map(clienteModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Cliente>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ClienteControllerV2.class).findALL()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }


}
