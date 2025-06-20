package com.rrios.msvc.productos.msvc_productos.controllers;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
@Tag(name = "Productos",description = "Operaciones CRUD")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(
            summary = "Obtiene todos los productos",
            description = "Devuelve un List de Productos en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Operación exitosa")}
    )
    public ResponseEntity<List<Producto>> findAll(){
        List<Producto> productos = this.productoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productos);
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
        Producto producto = this.productoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }

    @PostMapping
    @Operation(
            summary = "Guarda un producto",
            description = "Con este método podemos enviar datos a través de un body y crear un producto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Guardado exitoso."),
            @ApiResponse(
                    responseCode = "409",
                    description = "El producto ya se encuentra en la base de datos.",
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
            description = "Producto a crear.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class)
            )
    )
    public ResponseEntity<Producto> save(@Valid @RequestBody ProductoDTO productoDTO){
        Producto saved = this.productoService.save(productoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un producto por su Id.",
            description = "Elimina un producto de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Eliminación exitosa.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}



















