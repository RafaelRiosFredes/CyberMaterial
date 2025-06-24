package com.farancibia.msvc.inventarios.msvc_inventarios.controllers;

import com.farancibia.msvc.inventarios.msvc_inventarios.assemblers.InventarioModelAssembler;
import com.farancibia.msvc.inventarios.msvc_inventarios.dtos.InventarioDTO;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.entities.Inventario;
import com.farancibia.msvc.inventarios.msvc_inventarios.services.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping
@Validated
@Tag(name = "Inventario V2", description = "Operaciones CRUD de Inventario")
public class InventarioControllerV2 {


    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler inventarioModelAssembler;


    @GetMapping
    @Operation(
            summary = "Obtiene todos los productos",
            description = "Devuelve un List de Productos en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Producto.class)
                    )
            )}
    )

    public ResponseEntity<CollectionModel<EntityModel<InventarioDTO>>> findAll() {
        List<EntityModel<InventarioDTO>> entityModels = this.inventarioService.findAll()
                .stream()
                .map(inventarioModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<InventarioDTO>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/sucursales/{idSucursal}/productos/{idProducto}")
    @Operation(summary = "Busca inventario por idSucursal e idProducto",
            description = "Devuelve un inventario si existe")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Inventario encontrado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = InventarioDTO.class))),
            @ApiResponse(responseCode = "404",
                        description = "Inventario no encontrado")
    })
    public ResponseEntity<EntityModel<InventarioDTO>> getInventarioBySucursalAndProducto(
            @PathVariable Long idSucursal,
            @PathVariable Long idProducto) {

        InventarioDTO inventarioDTO = inventarioService.findByIdSucursalAndIdProducto(idSucursal, idProducto);

        EntityModel<InventarioDTO> entityModel = inventarioModelAssembler.toModel(inventarioDTO);

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    public ResponseEntity<Inventario> save(@Valid @RequestBody Inventario inventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.inventarioService.save(inventario));
    }

}


