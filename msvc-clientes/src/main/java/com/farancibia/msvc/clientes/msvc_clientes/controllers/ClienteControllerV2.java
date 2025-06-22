package com.farancibia.msvc.clientes.msvc_clientes.controllers;

import com.farancibia.msvc.clientes.msvc_clientes.assemblers.ClienteModelAssembler;
import com.farancibia.msvc.clientes.msvc_clientes.dtos.ClienteDTO;
import com.farancibia.msvc.clientes.msvc_clientes.dtos.ErrorDTO;
import com.farancibia.msvc.clientes.msvc_clientes.models.entities.Cliente;
import com.farancibia.msvc.clientes.msvc_clientes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/clientes")
@Validated
@Tag(name = "Clientes V2", description = "Operaciones CRUD de clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;


    @GetMapping
    @Operation(summary = "Obtiene todos los clientes",
            description = "Devuelve un list de Clientes en el body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
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

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene un cliente por su id",
            description = "Devuelve un cliente en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente no encontrado con el id administrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters(value = {
            @Parameter(name = "Id",description = "Este es el id unico del cliente",required = true)
    })

    public ResponseEntity<EntityModel<Cliente>> findById(@PathVariable Long id){
        EntityModel<Cliente> entityModel = this.clienteModelAssembler.toModel(
                this.clienteService.findById(id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un cliente",
            description = "Con este metodo podemos enviar datos a traves de un body y crear un cliente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El cliente ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Error",
                                    value = "{\"codigo\": \"statusCode\",\"date\":\"fecha\"}"
                            )
                    )
            )
    })

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Cliente a crear",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class)
            )
    )
    public ResponseEntity<EntityModel<Cliente>> save(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteNew = this.clienteService.save(clienteDTO);
        EntityModel<Cliente> entityModel = this.clienteModelAssembler.toModel(clienteNew);

        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).findById(clienteNew.getIdCliente())).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un cliente por su Id.",
            description = "Elimina un cliente de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Eliminación exitosa.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
