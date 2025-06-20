package com.rrios.msvc.productos.msvc_productos.controllers;

import com.rrios.msvc.productos.msvc_productos.assemblers.ProductoModelAssembler;
import com.rrios.msvc.productos.msvc_productos.dtos.ErrorDTO;
import com.rrios.msvc.productos.msvc_productos.dtos.ProductoDTO;
import com.rrios.msvc.productos.msvc_productos.models.entities.Producto;
import com.rrios.msvc.productos.msvc_productos.services.ProductoService;
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
@RequestMapping("/api/v2/productos")
@Validated
@Tag(name = "Productos V2",description = "Operaciones CRUD")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler productoModelAssembler;

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
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> findAll(){
        List<EntityModel<Producto>> entityModels = this.productoService.findAll()
                .stream()
                .map(productoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Producto>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene un producto por su id",
            description = "Devuelve un Producto en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Operación exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado con el id administrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)))})
    @Parameters(value = {
            @Parameter(name = "Id",description = "Este es el id unico del producto",required = true)})

    public ResponseEntity<Producto> findById(@PathVariable Long id){
        EntityModel<Producto> entityModel = this.productoModelAssembler.toModel(
                this.productoService.findById(id)
        );
        Producto producto = this.productoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un producto",
            description = "Con este metodo podemos enviar datos a traves de un body y crear un producto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El producto ya se encuentra en la base de datos",
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
            description = "Producto a crear",
            content = @Content(

            )
    )
    public ResponseEntity<EntityModel<Producto>> save(@Valid @RequestBody ProductoDTO productoDTO){
        Producto saved = this.productoService.save(productoDTO);
        EntityModel<Producto> entityModel = this.productoModelAssembler.toModel(saved);

        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).findById(saved.getIdProducto())).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}



















