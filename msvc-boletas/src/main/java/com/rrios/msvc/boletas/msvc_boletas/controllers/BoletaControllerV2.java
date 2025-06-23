package com.rrios.msvc.boletas.msvc_boletas.controllers;

import com.rrios.msvc.boletas.msvc_boletas.assemblers.BoletaDTOModelAssembler;
import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.services.BoletaService;
import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/boletas")
@Validated
@Tag(name = "Boletas V2",description = "Operaciones CRUD de boletas")
public class BoletaControllerV2 {
    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaDTOModelAssembler boletaDTOModelAssembler;

    @GetMapping
    @Operation(
            summary = "Obtiene todos las boletas",
            description = "Devuelve un List de boletas en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = BoletaDTO.class)
                    )
            ),
    })
    public ResponseEntity<CollectionModel<EntityModel<BoletaDTO>>> findAllDTOs(){
        List<EntityModel<BoletaDTO>> entityModels = this.boletaService.findAllDTOs()
                .stream()
                .map(boletaDTOModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<BoletaDTO>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(BoletaControllerV2.class).findAllDTOs()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene una boleta por su id",
            description = "Devuelve una boleta en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa.",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = BoletaDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada con el id administrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "Id",
            description = "Este es el id unico de la boleta",required = true)})
    public ResponseEntity<EntityModel<BoletaDTO>> findDTOById(@PathVariable Long id){
        EntityModel<BoletaDTO> entityModel = this.boletaDTOModelAssembler.toModel(
                this.boletaService.findDTOById(id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda una boleta",
            description = "Con este método podemos enviar datos a través de un body y crear un producto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso.",
            content = @Content(
                    mediaType = MediaTypes.HAL_JSON_VALUE,
                    schema = @Schema(implementation = BoletaDTO.class)
            )),
            @ApiResponse(
                    responseCode = "409",
                    description = "La boleta ya se encuentra en la base de datos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =  ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Boleta a crear.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BoletaDTO.class)
            )
    )
    public ResponseEntity<Boleta> save(@Valid @RequestBody BoletaDTO boleta){
        return ResponseEntity.status((HttpStatus.CREATED)).body(this.boletaService.save(boleta));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina una boleta por su Id.",
            description = "Elimina una boleta de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Eliminación exitosa.")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Long id){
        this.boletaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
